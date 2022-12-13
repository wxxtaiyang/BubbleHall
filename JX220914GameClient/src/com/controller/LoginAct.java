package com.controller;

import com.alibaba.fastjson.JSONObject;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginAct implements ActionListener {
    private Controller con;

    public LoginAct(Controller con) {
        this.con = con;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("login")){
            String acc = con.lFrame.lPanel.getAcc().getText();
            String pwd = new String(con.lFrame.lPanel.getPwd().getPassword());
            JSONObject js = new JSONObject();
            js.put("action","login");
            js.put("acc",acc);
            js.put("pwd",pwd);
            con.link.sendMessage(js.toString());
        }else if(e.getActionCommand().equals("register")){
            con.lFrame.setVisible(false);
            con.rFrame.setVisible(true);
        }
    }
}
