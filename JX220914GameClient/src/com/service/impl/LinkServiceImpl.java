package com.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.controller.Controller;
import com.net.ClientLink;
import com.service.BusinessService;
import com.util.Action;
import com.view.game.GHallPanel;
import model.User;

@Action("link")
public class LinkServiceImpl implements BusinessService {
    private Controller con;
    @Override
    public void doBusinessService(JSONObject js, ClientLink link) {

        User user = js.getObject("user",User.class);
        con.ghFrame.ghtake.getListModel().addElement(user.getNickname()+"：登录游戏");
        GHallPanel.users.add(user);
        con.ghFrame.ghPanel.setTreeModels();
        System.out.println("link后的数"+GHallPanel.users);
    }
}
