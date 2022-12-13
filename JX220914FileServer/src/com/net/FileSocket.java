package com.net;

import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.net.Socket;

public class FileSocket extends Thread {
    private Socket socket;
    private String filename;
    private String type;


    public FileSocket(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            // 输入流如果是本地拿到的，那么就是要上传文件到客户端
            // 输出流如果是本地拿到的，那么就是要下载文件到本地
            InputStream socketIn = socket.getInputStream();
            OutputStream socketOs = socket.getOutputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(socketIn));
            String line = reader.readLine();
            if(line == null) return;
            JSONObject js = JSONObject.parseObject(line);
            filename = js.getString("name");
            type = js.getString("type");
            if(js.getString("action").equals("down")) {// 客户端下载
                PrintWriter writer = new PrintWriter(new OutputStreamWriter(socketOs));
                writer.println(js);
                writer.flush();
                Thread.sleep(500);
                BufferedInputStream in = new BufferedInputStream(new FileInputStream(filename));
                DataOutputStream dataOs = new DataOutputStream(socketOs);
                byte[] buffer = new byte[1024];
                int len = 0;
                while ((len = in.read(buffer)) != -1){
                    dataOs.write(buffer);
                    dataOs.flush();
                }
                in.close();
            }else{ // 客户端上传
                BufferedInputStream in = new BufferedInputStream(socketIn);
                DataOutputStream dataOs = new DataOutputStream(new FileOutputStream("resources\\"+type+"\\"+filename));

                byte[] buffer = new byte[1024];
                int len = 0;
                while ((len = in.read(buffer)) != -1){
                    dataOs.write(buffer);
                    dataOs.flush();
                }
                dataOs.close();
            }
            Thread.sleep(200);
            socketIn.close();
            socketOs.close();
            socket.close();
        }catch (Exception e){
            System.err.println("文件服务器错误");
            e.printStackTrace();
        }
    }
}
