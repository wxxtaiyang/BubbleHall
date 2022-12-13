package com.controller;

import com.controller.game.*;
import com.net.ClientConnect;
import com.net.ClientHeart;
import com.net.ClientLink;
import com.service.BusinessService;
import com.util.Action;
import com.view.game.*;
import com.view.welcome.LoginFrame;
import com.view.welcome.RegisterFrame;
import model.User;

import java.awt.*;
import java.io.File;
import java.lang.reflect.Field;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.*;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class Controller {
    public ClientHeart heart;// 心跳线程
    public ClientConnect connect;// 连接线程
    public ClientLink link;// 接收线程
    public static Map<String, BusinessService> SERVICES = new HashMap<>();// 事务工厂
    public static List<User> allUser = new ArrayList<>(); // 全部用户

    public AllWindowAct winAct;// 页面关闭监听类
    public LoginAct loginAct;// 登录动作监听
    public RegisterAct regAct;// 注册动作监听
    public TakeAct takeAct;// 聊天按钮监听
    public TreeAct treeAct;// 树节点监听
    public PersonAct personAct;// 个人中心监听
    public RoomAct roomAct;// 房间监听
    public WaitAct waitAct;// 等待界面监听
    public JoinRoomAct joinRoomAct;// 加入房间界面监听
    public GameKeyAct gameKeyAct;// 按键监听


    public LoginFrame lFrame;// 登录界面
    public RegisterFrame rFrame;// 注册界面
    public GHallFrame ghFrame;// 游戏大厅
    public GameRoomFrame grFrame;// 游戏房间
    public PersonalFrame pFrame;// 个人中心界面
    public WaitGameFrame wgFrame;// 准备界面
    public MapFrame mFrame;// 地图界面


    public Font font;
    public Font pwdFont;

    public Controller() {
        try {
            List<Class<?>> a = scanJar("com.service.impl");
            for (Class<?> aClass : a) {
                String path = aClass.getName().replaceAll("\\$1","");
                System.out.println(path);
                Class clazz = Class.forName(path);
                BusinessService bs = (BusinessService) clazz.newInstance();
                Field field = clazz.getDeclaredField("con");
                field.setAccessible(true);
                field.set(bs, this);
                Action action = (Action) clazz.getAnnotation(Action.class);
                SERVICES.put(action.value(), bs);
            }

//            File file = new File("src/com/service/impl");
//            File[] files = file.listFiles();
//            for (File f : files) {
//                String path = "com.service.impl."+f.getName().replaceAll(".java","");
//                Class clazz = Class.forName(path);
//                BusinessService bs = (BusinessService) clazz.newInstance();
//                Field field = clazz.getDeclaredField("con");
//                field.setAccessible(true);
//                field.set(bs, this);
//                Action action = (Action) clazz.getAnnotation(Action.class);
//                SERVICES.put(action.value(), bs);
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        font = new Font("华文琥珀", Font.PLAIN, 20);
        pwdFont = new Font("宋体", Font.BOLD, 20);


        winAct = new AllWindowAct();
        loginAct = new LoginAct(this);
        regAct = new RegisterAct(this);
        takeAct = new TakeAct(this);
        treeAct = new TreeAct(this);
        personAct = new PersonAct(this);
        roomAct = new RoomAct(this);
        waitAct = new WaitAct(this);
        joinRoomAct = new JoinRoomAct(this);
        gameKeyAct = new GameKeyAct(this);

        lFrame = new LoginFrame(this);
        rFrame = new RegisterFrame(this);
        ghFrame = new GHallFrame(this);
        grFrame = new GameRoomFrame(this);
        pFrame = new PersonalFrame(this);
        wgFrame = new WaitGameFrame(this);
        mFrame = new MapFrame(this);

        connect = new ClientConnect(this);// 实例化连接线程并启动
        connect.start();

        ghFrame.ghtake.gHallImage.getPosition();
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
