package com.controller.game;

import com.alibaba.fastjson.JSONObject;
import com.controller.Controller;
import com.view.game.GHallFrame;
import com.view.game.PersonalFrame;
import model.Role;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;

public class PersonAct implements ActionListener {
    private Controller con;

    public PersonAct(Controller con) {
        this.con = con;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("sure")){
            PersonalFrame.role.setName(con.ghFrame.getUser().getUsername());
            Class clazz1 = con.pFrame.pPanel.getHatIcon().getClass();
            try {
                Field field1 = clazz1.getDeclaredField("filename");
                field1.setAccessible(true);
                PersonalFrame.role.setImage((String) field1.get(con.pFrame.pPanel.getPersonIcon()));
                PersonalFrame.role.setHat((String) field1.get(con.pFrame.pPanel.getHatIcon()));
                PersonalFrame.role.setClothes((String) field1.get(con.pFrame.pPanel.getClothesIcon()));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            JSONObject js = new JSONObject();
            js.put("action","role");
            js.put("role",PersonalFrame.role);
            con.link.sendMessage(js.toString());
        }else if(e.getActionCommand().equals("back")){
            con.pFrame.setVisible(false);
            con.ghFrame.setVisible(true);
        }else if(e.getActionCommand().equals("upHat")){

        }
    }
}
