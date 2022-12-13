package com.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.controller.Controller;
import com.net.ClientFile;
import com.net.ClientLink;
import com.service.BusinessService;
import com.util.Action;
import com.util.GameMusic;
import com.view.game.*;
import model.*;

import javax.swing.*;
import java.util.List;

@Action("login")
public class LoginServiceImpl implements BusinessService {
    private Controller con;

    @Override
    public void doBusinessService(JSONObject js, ClientLink link) {
        boolean flag = js.getBoolean("flag");
        if(flag){
            JOptionPane.showMessageDialog(null,"登录成功");
            Controller.allUser = js.getJSONArray("users").toJavaList(User.class);// 拿到所有用户集合
            GHallPanel.users = js.getJSONArray("link").toJavaList(User.class);// 拿到登录上的用户集合

//            System.out.println("客户端：得到登录人数"+GHallPanel.users);
            con.ghFrame.setUser(js.getObject("u",User.class));// 拿到当前登录的用户类
            List<Record> records = js.getJSONArray("all").toJavaList(Record.class);// 拿到历史记录
            List<Dress> dresses = js.getJSONArray("dress").toJavaList(Dress.class);// 拿到文件信息
            for (Dress dress : dresses) {
                ClientFile clientFile = new ClientFile(dress.getImage(),"down",dress.getName());
                clientFile.start();
            }
            Role role = js.getObject("role",Role.class);
            if(role != null)
                PersonalFrame.role = role;
            GameRoomPanel.roomList = js.getJSONArray("rooms").toJavaList(Room.class);
            con.pFrame.pPanel.changeShowImage();// 修改图片信息
            GHallTake.history.put("all",records);
            con.ghFrame.ghtake.setModels("all");
            con.ghFrame.ghPanel.setTreeModels();
            con.lFrame.setVisible(false);
            con.ghFrame.setVisible(true);

        }else {
            JOptionPane.showMessageDialog(null
                    ,"登录失败，账号或密码错误(也有可能别人登你号了)！","",JOptionPane.ERROR_MESSAGE);
        }
    }
}
