package com.net;

import com.alibaba.fastjson.JSONObject;
import model.Dress;

import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.util.Properties;

public class ClientFile extends Thread {
    private Socket socket;
    private String name;
    private String action;
    private String type;

    public ClientFile(String name,String action,String type) {
        Properties ps = new Properties();
        try {
            ps.load(new FileInputStream("db.properties"));
            this.socket = new Socket("localhost", Integer.parseInt(ps.getProperty("filePort")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.name = name;
        this.action = action;
        this.type = type;
    }
    @Override
    public void run() {
        try {
            // 输入流如果是本地拿到的，那么就是要上传文件到服务端
            // 输出流如果是本地拿到的，那么就是要下载文件到本地
            InputStream socketIn = socket.getInputStream();
            OutputStream socketOs = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(socketOs));
            JSONObject jsp = new JSONObject();
            jsp.put("action",action);
            jsp.put("name",name);
            jsp.put("type",type);
            writer.println(jsp.toString());
            writer.flush();// 发送给服务器让他知道我们要干啥

            BufferedReader reader = new BufferedReader(new InputStreamReader(socketIn));
            String line = reader.readLine();
            if(line == null) return;
            JSONObject js = JSONObject.parseObject(line);
            if(js.getString("action").equals("up")) {// 客户端上传
                Thread.sleep(100);
                BufferedInputStream in = new BufferedInputStream(new FileInputStream(name));
                DataOutputStream dataOs = new DataOutputStream(socketOs);
                byte[] buffer = new byte[1024];
                int len = 0;
                while ((len = in.read(buffer)) != -1){
                    dataOs.write(buffer);
                    dataOs.flush();
                }
                in.close();
            }else{ // 我们下载
                String filename = js.getString("name").split("\\\\")[js.getString("name").split("\\\\").length-1];
                String filepath = "resources\\"+type;
                File file = new File(filepath);
                if(!file.exists()) // 判断文件夹是否存在
                    file.mkdirs();

                BufferedInputStream in = new BufferedInputStream(new DataInputStream(socketIn));
                DataOutputStream dataOs = new DataOutputStream(new FileOutputStream(filepath+"\\"+filename));
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
            e.printStackTrace();
        }
    }
}
