package com.controller;

import com.net.ServerAccept;
import com.net.SocketLink;
import com.service.BusinessService;
import com.util.Action;
import model.Room;

import java.io.File;
import java.lang.reflect.Field;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class Controller {
    public static Map<String, SocketLink> LINKS = new HashMap<>(); // 已登录的用户和他的线程集合
    public static Map<String, BusinessService> SERVICES = new HashMap<>();// 事务集合
    public static List<Room> rooms = new ArrayList<>();


    public Controller() {
        List<Class<?>> a = scanJar("com.service.impl");
        for (Class<?> aClass : a) {
            String path = aClass.getName().replaceAll("\\$1","");
            System.out.println(path);
            try {
                Class clazz = Class.forName(path);
                BusinessService bs = (BusinessService) clazz.newInstance();
//                Field field = clazz.getDeclaredField("con");
//                field.setAccessible(true);
//                field.set(bs, this);
                Action action = (Action) clazz.getAnnotation(Action.class);
                SERVICES.put(action.value(), bs);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

//        File file = new File("JX220914GameServer/src/com/service/impl");
//        File[] files = file.listFiles();
//        for (File f : files) {
//            String path = "com.service.impl."+f.getName().replaceAll(".java","");
//            try {
//                Class clazz = Class.forName(path);
//                Action action = (Action) clazz.getAnnotation(Action.class);
//                SERVICES.put(action.value(),(BusinessService) clazz.newInstance());
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }

        ServerAccept serverAccept = new ServerAccept(); // 创建服务器监听线程
        serverAccept.start(); // 启动该线程
    }

    public List<Class<?>> scanJar(String basePackage){
        List<Class<?>> classList = new ArrayList<>();
        try {
            //通过当前线程得到类加载器从而得到URL的枚举
            Enumeration<URL> urlEnumeration = Thread.currentThread().getContextClassLoader().getResources(basePackage.replace(".","/"));
            while (urlEnumeration.hasMoreElements()){
                URL url = urlEnumeration.nextElement();
                String protocol = url.getProtocol();
                if("jar".equalsIgnoreCase(protocol)){
                    //转换为JarURLConnection
                    JarURLConnection connection = (JarURLConnection) url.openConnection();
                    if(connection != null){
                        //得到该jar文件下面的类实体
                        JarFile jarFile = connection.getJarFile();
                        if(jarFile != null){
                            Enumeration<JarEntry> jarEntryEnumeration = jarFile.entries();
                            while(jarEntryEnumeration.hasMoreElements()){
                                JarEntry entry = jarEntryEnumeration.nextElement();
                                String jarEntryName = entry.getName();
                                if(jarEntryName.contains(".class") && jarEntryName.replace("/",".").startsWith(basePackage)){
                                    String className = jarEntryName.substring(0, jarEntryName.lastIndexOf(".")).replace("/", ".");
                                    Class cls = Class.forName(className);
                                    System.out.println(cls);
                                    classList.add(cls);
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return classList;
    }
}
