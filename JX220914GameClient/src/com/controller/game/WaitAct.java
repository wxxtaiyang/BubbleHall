package com.controller.game;

import com.alibaba.fastjson.JSONObject;
import com.controller.Controller;
import com.view.game.GameRoomPanel;
import com.view.game.PersonalPanel;
import com.view.game.WaitGamePanel;
import model.Room;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WaitAct implements ActionListener {
    private Controller con;

    public WaitAct(Controller con) {
        this.con = con;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("ready")) {
            boolean flag = false;
            if (GameRoomPanel.room.getMid().equals(con.ghFrame.getUser().getUsername())) {
                if (con.wgFrame.wgPanel.isP1Ready())
                    flag = false;
                else
                    flag = true;
            } else {
                if (con.wgFrame.wgPanel.isP2Ready())
                    flag = false;
                else
                    flag = true;
            }
            JSONObject js = new JSONObject();
            js.put("action", "room");
            js.put("type", "ready");
            js.put("room", GameRoomPanel.room);
            js.put("user", con.ghFrame.getUser().getUsername());
            js.put("flag", flag);
            con.link.sendMessage(js.toString());
        } else if (e.getActionCommand().equals("exit")) {
            Room room = GameRoomPanel.room;
            JSONObject js = new JSONObject();
            js.put("action", "room");// 动作，房间事件
            js.put("type", "exit");// 类型，退出
            js.put("room", room);// 房间
            js.put("user", con.ghFrame.getUser().getUsername());// 退出者
            con.link.sendMessage(js.toString());
        }
    }
}
