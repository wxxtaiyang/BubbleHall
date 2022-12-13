package com.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.controller.Controller;
import com.net.ClientLink;
import com.service.BusinessService;
import com.util.Action;
import com.view.game.GHallPanel;
import model.User;

@Action("close")
public class CloseServiceImpl implements BusinessService {
    private Controller con;

    @Override
    public void doBusinessService(JSONObject js, ClientLink link) {
        User user = js.getObject("user",User.class);
        con.ghFrame.ghtake.getListModel().addElement(user.getNickname()+"：退出游戏");

        for (User u : GHallPanel.users) {
            if(u.getUsername().equals(user.getUsername())){
                GHallPanel.users.remove(u);
                break;
            }
        }
        con.ghFrame.ghPanel.setTreeModels();
        System.out.println("close后的数"+GHallPanel.users);
    }
}
