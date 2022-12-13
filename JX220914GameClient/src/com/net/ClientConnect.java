package com.net;

import com.alibaba.fastjson.JSONObject;
import com.controller.Controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Properties;

public class ClientConnect extends Thread {
    private Controller con;

    public ClientConnect(Controller con) {
        this.con = con;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Properties p = new Properties();
                p.load(new FileInputStream("db.properties"));
                String address = p.getProperty("address");
                String port = p.getProperty("linkPort");
                Socket socket = new Socket(address, Integer.parseInt(port));// 参试连接
                if(con.link == null) // 判断是否为空
                    con.link = new ClientLink(socket);// 新建连接服务器线程
                else
                    con.link.setSocket(socket);// 重新载入socket套接字，其他数据不变
                con.link.start();// 启动接收信息
                con.heart = new ClientHeart(con.link,con);// 新建心跳线程
                con.heart.start();// 启动心跳
                return;// 结束方法，线程结束
            } catch (Exception e) {
                System.err.println("连接失败!");
            }
            try {
                Thread.sleep(2000);
            } catch (Exception e) {
                System.err.println("睡眠错误！");
            }
        }
    }

}
