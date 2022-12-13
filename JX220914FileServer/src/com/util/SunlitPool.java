package com.util;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.lang.reflect.Proxy;
import java.net.SocketTimeoutException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.Properties;

public class SunlitPool {

    private LinkedList<Connection> ctns;
    private int initialLink;// 初始链接数
    private int maxLink;// 最大链接数
    private int maxSize;// 当前类总存在的连接数
    private int minLink;// 最小链接数
    private int linkTime;// 等待时长
    private Timer timer;// 定时器
    private int countDown;// 倒计时
    private static SunlitPool SQLPOOL;

    private ActionListener act = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(ctns.size() > 0 || countDown == 0){
                timer.stop();
            }
            countDown--;

        }
    };
    // 初始化，给予自定义的设置值
    private SunlitPool() {
        ctns = new LinkedList<>();
        timer = new Timer(10,act);
        try {
            Properties p = new Properties();
            p.load(new FileInputStream("db.properties"));
            this.initialLink = Integer.parseInt(p.getProperty("initialSize"));
            this.maxLink = Integer.parseInt(p.getProperty("maxActive"));
            this.minLink = Integer.parseInt(p.getProperty("minActive"));
            this.linkTime = Integer.parseInt(p.getProperty("maxWait"));
            this.maxSize = initialLink;
            Class.forName(p.getProperty("driver"));
            for (int i = 1; i <= this.initialLink; i++) {
                Connection ctn = DriverManager.getConnection(p.getProperty("url"), p.getProperty("userName"),
                        p.getProperty("userPwd"));
                ctns.add(ctn);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取连接池实例
     */
    public static SunlitPool getInstance(){
        if(SQLPOOL == null){
            SQLPOOL = new SunlitPool();
        }
        return SQLPOOL;
    }
    /**
     * 获取连接
     * @return
     */
    public Connection getConnection() {
        if(ctns.size() == 0){
            countDown = linkTime;// 连接时间
            timer.start();// 程序计数器开始执行
            while(timer.isRunning());// 计数器开始执行，线程停止等待计数器
            if(countDown == 0){// 判断时间是否归零
                new SocketTimeoutException().printStackTrace();
                return null;// 抛出连接超时异常
            }
        }
        Connection ctn = ctns.remove();
        // 代理模式实现
        Connection connection = (Connection) Proxy.newProxyInstance(ctn.getClass().getClassLoader()
                ,ctn.getClass().getInterfaces(),new ConnectionInvocationHandler(ctn,ctns,maxLink));
        if(ctns.size() < minLink && maxSize < maxLink){// 当当前连接池最小连接小于设置值且最大还未到最大连接时
            try {
                Properties p = new Properties();
                p.load(new FileInputStream("db.properties"));
                ctns.add(DriverManager.getConnection(p.getProperty("url"), p.getProperty("userName"),
                        p.getProperty("userPwd")));
                maxSize++;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
    /**
     * 关闭数据库有关所有对象
     * @param ctn  连接对象
     * @param ps 执行者对象
     * @param rs 返回值对象
     */
    public void closeAll(Connection ctn,PreparedStatement ps,ResultSet rs){
        try{
            if(rs != null){
                rs.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        try{
            if(ps != null){
                ps.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        try{
            if(ctn != null){
                ctn.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
