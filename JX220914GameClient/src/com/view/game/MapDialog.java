package com.view.game;

import com.alibaba.fastjson.JSONObject;
import com.commen.ImageData;
import com.controller.Controller;
import model.Room;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MapDialog extends JDialog {
    private Controller con;

    public MapDialog(Controller con) {
        this.con = con;
        setResizable(false);
        setSize(new Dimension(400,400));
        setLocationRelativeTo(null);
        setLayout(new GridLayout(2,2,10,10));

        ActionListener act = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MapPanel.mapEvel = Integer.valueOf(e.getActionCommand());
                MapPanel.map = MapPanel.maps[MapPanel.mapEvel];
                Room room = new Room();
                room.setMid(con.ghFrame.getUser().getUsername());
                room.setAgainstA(con.ghFrame.getUser().getUsername());
                room.setWinner("等待");
                JSONObject js = new JSONObject();
                js.put("action","room");
                js.put("type","create");
                js.put("room",room);
                con.link.sendMessage(js.toString());
            }
        };

        JButton btn1 = new JButton(ImageData.map1);
        btn1.setActionCommand("0");
        btn1.addActionListener(act);
        add(btn1);
        JButton btn2 = new JButton(ImageData.map2);
        btn2.setActionCommand("1");
        btn2.addActionListener(act);
        add(btn2);
        JButton btn3 = new JButton(ImageData.map3);
        btn3.setActionCommand("2");
        btn3.addActionListener(act);
        add(btn3);
        JButton btn4 = new JButton(ImageData.map4);
        btn4.setActionCommand("3");
        btn4.addActionListener(act);
        add(btn4);

    }
}
