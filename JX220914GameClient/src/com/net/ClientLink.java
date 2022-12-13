package com.net;

import com.alibaba.fastjson.JSONObject;
import com.controller.Controller;

import java.io.*;
import java.net.Socket;

public class ClientLink extends Thread {
    private Socket socket;
    private PrintWriter writer;
    private BufferedReader reader;

    public ClientLink(Socket socket) {
        this.socket = socket;// 得到socket并且得到输入于输出流
        try {
            writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                String line = reader.readLine(); // 读取等待阻塞
                if (line == null) return;

                JSONObject js = JSONObject.parseObject(line); // 转为json格式
                Controller.SERVICES.get(js.getString("action")).doBusinessService(js,this);
            }
        } catch (Exception e) {
            System.err.println("服务器连接错误！");
            e.printStackTrace();
        }
    }

    /**
     * 将字符串输出到服务器
     *
     * @param str 输出到服务器数据(JSON格式)
     */
    public void sendMessage(String  str) {
//        System.out.println("发送"+str);
        writer.println(str);
        writer.flush();
    }

    /**
     * 重新载入socket
     * @param socket socket
     */
    public void setSocket(Socket socket) {
        this.socket = socket;
        try {
            writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
