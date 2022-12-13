package com.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.dao.UserDao;
import com.dao.impl.UserDaoImpl;
import com.net.SocketLink;
import com.service.BusinessService;
import com.util.Action;
import model.User;

@Action("register")
public class RegisterServiceImpl implements BusinessService {
    UserDao userDao = new UserDaoImpl();
    @Override
    public void doBusinessService(JSONObject js, SocketLink link) {
        User user = js.getObject("user", User.class);
        int flag = userDao.insertUser(user);
        js.put("flag",flag);
        link.sendMessage(js.toString());
    }
}
