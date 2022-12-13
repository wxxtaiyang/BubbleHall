package com.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.controller.Controller;
import com.net.ClientLink;
import com.service.BusinessService;
import com.util.Action;

import javax.swing.*;

@Action("register")
public class RegisterServiceImpl implements BusinessService {
    private Controller con;

    @Override
    public void doBusinessService(JSONObject js, ClientLink link) {
        int flag = js.getInteger("flag");
        if(flag == -1){
            JOptionPane.showMessageDialog(null,"账号重复",""
                    ,JOptionPane.ERROR_MESSAGE);
        }else if(flag == 0){
            JOptionPane.showMessageDialog(null,"注册失败！",""
                    ,JOptionPane.ERROR_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(null,"注册成功");
            con.rFrame.setVisible(false);
            con.lFrame.setVisible(true);
        }
    }
}
