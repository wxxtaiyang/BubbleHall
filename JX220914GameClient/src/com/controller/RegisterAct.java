package com.controller;

import com.alibaba.fastjson.JSONObject;
import model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterAct implements ActionListener {
    private Controller con;

    public RegisterAct(Controller con) {
        this.con = con;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("login")){
            con.lFrame.setVisible(true);
            con.rFrame.setVisible(false);
        }else if(e.getActionCommand().equals("register")){
            String acc = con.rFrame.rPanel.getAcc().getText();
            String uname = con.rFrame.rPanel.getUname().getText();
            String pwd = new String(con.rFrame.rPanel.getPwd().getPassword());
            String checkPwd = new String(con.rFrame.rPanel.getCheckPwd().getPassword());
            if(acc.length() == 0 || uname.length() == 0 || pwd.length() == 0){
                JOptionPane.showMessageDialog(null,"数据不能为空",""
                        ,JOptionPane.ERROR_MESSAGE);
                return;
            }
            if(acc.indexOf(" ") != -1 || uname.indexOf(" ") != -1 || pwd.indexOf(" ") != -1){
                JOptionPane.showMessageDialog(null,"不能存在空字符",""
                        ,JOptionPane.ERROR_MESSAGE);
                return;
            }
            if(!pwd.equals(checkPwd)){
                JOptionPane.showMessageDialog(null,"两次密码不一致",""
                        ,JOptionPane.ERROR_MESSAGE);
                return;
            }
            User user = new User();
            user.setUsername(acc);
            user.setNickname(uname);
            user.setPassword(pwd);
            JSONObject js = new JSONObject();
            js.put("action","register");
            js.put("user",user);
            System.out.println(user);
            con.link.sendMessage(js.toString());
        }
    }
}
