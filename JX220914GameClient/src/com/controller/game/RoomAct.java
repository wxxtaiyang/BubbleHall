package com.controller.game;

import com.alibaba.fastjson.JSONObject;
import com.controller.Controller;
import com.view.game.GameRoomPanel;
import com.view.game.PersonalFrame;
import model.Room;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RoomAct implements ActionListener {
    private Controller con;

    public RoomAct(Controller con) {
        this.con = con;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("create")){
            con.grFrame.grPanel.dialog.setVisible(true);
        }else if(e.getActionCommand().equals("exit")){
            con.grFrame.setVisible(false);
            con.ghFrame.setVisible(true);
        }else if(e.getActionCommand().equals("fast")){
            for (JButton btn : con.grFrame.grPanel.getRoomBtns()) {
                System.out.println(btn.getText());
                String[] rooms = btn.getText().split("-");
                if(rooms[1].equals("等待")){
                    String name = rooms[0];
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
        }
    }
}
