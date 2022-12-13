package com.controller.game;

import com.alibaba.fastjson.JSONObject;
import com.controller.Controller;
import com.view.game.GameRoomPanel;
import com.view.game.PersonalFrame;
import com.view.game.PersonalPanel;
import model.Room;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JoinRoomAct implements ActionListener {
    private Controller con;

    public JoinRoomAct(Controller con) {
        this.con = con;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String name = e.getActionCommand().split("-")[0];
        for (Room room : GameRoomPanel.roomList) {
            if (room.getMid().equals(name) && !room.getWinner().equals("等待")) {
                JOptionPane.showMessageDialog(null, "房间人员已满，无法进入", ""
                        , JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
//        System.out.println(con.ghFrame.getUser().getUsername()+"---"+name+"---"+type);

        for (Room room : GameRoomPanel.roomList) {
            if (room.getMid().equals(name)) {
                JSONObject js = new JSONObject();
                js.put("action", "room");
                js.put("type", "join");
                room.setAgainstB(con.ghFrame.getUser().getUsername());
                room.setWinner("满员");
                js.put("room", room);
                GameRoomPanel.room = room;
                js.put("role", PersonalFrame.role);
                con.link.sendMessage(js.toString());
                System.out.println(name);
                break;
            }
        }
    }
}
