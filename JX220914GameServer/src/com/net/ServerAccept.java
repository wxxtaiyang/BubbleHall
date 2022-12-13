package com.net;

import model.User;

import java.io.FileInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ServerAccept extends Thread{
    public static List<User> users = new ArrayList<>();// 已登录用户集合
    @Override
    public void run() {
        try {
            Properties p = new Properties();
            p.load(new FileInputStream("db.properties"));
            String port = p.getProperty("linkPort");
            ServerSocket serverSocket = new ServerSocket(Integer.parseInt(port));
            while(true){
                Socket socket = serverSocket.accept();
                SocketLink link = new SocketLink(socket);// 新建一个接收客户端信息线程
                link.start();// 启动线程
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
