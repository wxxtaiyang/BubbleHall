package com.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.controller.Controller;
import com.dao.*;
import com.dao.impl.*;
import com.net.ServerAccept;
import com.net.SocketLink;
import com.service.BusinessService;
import com.util.Action;
import model.User;

import java.util.ArrayList;

@Action("login")
public class LoginServiceImpl implements BusinessService {
    UserDao userDao = new UserDaoImpl();
    RecordDao recordDao = new RecordDaoImpl();
    DressDao dressDao = new DressDaoImpl();
    RoleDao roleDao = new RoleDaoImpl();
    RoomDao roomDao = new RoomDaoImpl();

    @Override
    public void doBusinessService(JSONObject js, SocketLink link) {
        String acc = js.getString("acc");
        String pwd = js.getString("pwd");
        ArrayList<User> users = userDao.getUserByIdAndPwd(acc,pwd);
        if(users.size()  == 0){
            js.put("flag",false);
        }else{
            User user = users.get(0);
            for (User u : ServerAccept.users) {
                if(u.getUsername().equals(user.getUsername())){// 判断该用户是否已经在线
                    js.put("flag",false);
                    link.sendMessage(js.toString());
                    return;
                }
            }
            System.out.println(user.getUsername()+"：登录上线");
            link.setUser(user);
            ServerAccept.users.add(user);
            js.put("flag",true);
            js.put("link",ServerAccept.users);
//            System.out.println("服务器：当前登录人数"+ServerAccept.users.size());
            js.put("users",userDao.getAllUser());
            js.put("u",user);
            js.put("all",recordDao.selectAllRecord("","all"));
            js.put("dress",dressDao.selectAllDress());
            js.put("role",roleDao.selectRoleByUserId(user.getUsername()));
            Controller.rooms = roomDao.selectAll();
            js.put("rooms",Controller.rooms);
            for (SocketLink value : Controller.LINKS.values()) {// 遍历当前已连接用户
                JSONObject jsp = new JSONObject();
                jsp.put("action","link");
                jsp.put("user",user);
                value.sendMessage(jsp.toString());// 通知他们有新用户上线了
            }
            Controller.LINKS.put(acc,link);
        }
        link.sendMessage(js.toString());
    }
}
