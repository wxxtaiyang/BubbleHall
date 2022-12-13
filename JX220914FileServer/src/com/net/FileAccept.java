package com.net;

import java.io.FileInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;

public class FileAccept extends Thread {
    @Override
    public void run() {
        try {
            Properties p = new Properties();
            p.load(new FileInputStream("db.properties"));
            String port = p.getProperty("filePort");// 配置文件读取文件服务端的端口号
            ServerSocket serverSocket = new ServerSocket(Integer.parseInt(port));// 使用该端口创建服务器
            while (true){// 循环监听客户端连接
                Socket socket = serverSocket.accept();// 等待客户端连接
                FileSocket fileSocket = new FileSocket(socket);
                fileSocket.start();// 开启线程让客户端去完成文件操作。该线程继续监听其他客户端连接
            }
        }catch (Exception e){
            System.err.println("文件服务端错误");
            e.printStackTrace();
        }

    }
}
