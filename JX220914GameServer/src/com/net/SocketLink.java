package com.net;

import com.alibaba.fastjson.JSONObject;
import com.controller.Controller;
import com.dao.RoomDao;
import com.dao.impl.RoomDaoImpl;
import model.Room;
import model.User;

import java.io.*;
import java.net.Socket;

public class SocketLink extends Thread {
    private Socket socket;
    private PrintWriter writer;
    private BufferedReader reader;
    private User user;
    private RoomDao roomDao = new RoomDaoImpl();

    public SocketLink(Socket socket) {
        this.socket = socket;
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
                String line = reader.readLine();
                if (line == null) break;

                JSONObject js = JSONObject.parseObject(line); // 转为json格式
                Controller.SERVICES.get(js.getString("action")).doBusinessService(js, this);

            }
        } catch (IOException e) {
            if(user != null){
                Controller.LINKS.remove(user.getUsername());// 移除存储的线程

                for (User u : ServerAccept.users) {
                    if(u.getUsername().equals(user.getUsername())){
                        ServerAccept.users.remove(u);// 移除出已登录的集合
                        break;
                    }
                }
                System.err.println(user.getUsername()+"：断开连接");
                JSONObject js = new JSONObject();
                js.put("action","close");
                js.put("user",user);
                for (SocketLink link : Controller.LINKS.values()) {
                    link.sendMessage(js.toString());
                }
                Room room = new Room();
                room.setMid(user.getUsername());
                JSONObject jsp = new JSONObject();
                jsp.put("action","room");
                jsp.put("type","del");
                jsp.put("room",room);
                for (String s : Controller.LINKS.keySet()) {// 告知所有人房间被删除
                    Controller.LINKS.get(s).sendMessage(jsp.toString());
                }
                // 修改数据库，删除该房间
                roomDao.deleteRoom(room);
            }
        }

    }

    /**
     * 回应服务端
     *
     * @param str 回应数据
     */
    public void sendMessage(String str) {
        writer.println(str);
        writer.flush();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
