package com.dao.impl;

import com.dao.UserDao;
import com.util.DBUtils;
import model.User;

import java.util.ArrayList;

public class UserDaoImpl implements UserDao {
    DBUtils<User> dbUtils = new DBUtils<>();
    @Override
    public ArrayList<User> getUserByIdAndPwd(String acc, String pwd) {
        ArrayList<User> users = new ArrayList<>();
        ArrayList<Object> parameters = new ArrayList<>();
        parameters.add(acc);
        parameters.add(pwd);
        String sql = "select * from user where username=? and password=?";
        users = dbUtils.selectData(sql,parameters,User.class);
        return users;
    }

    @Override
    public int insertUser(User user) {
        ArrayList<Object> parameters = new ArrayList<>();
        parameters.add(user.getUsername());
        parameters.add(user.getNickname());
        parameters.add(user.getPassword());
        if(checkUserByUserName(user.getUsername()) == 0){
            return -1;
        }
        String sql = "insert into user(username,nickname,password,time) values(?,?,?,now())";
        if( dbUtils.updateData(sql,parameters)){
            return 1;
        }else {
            return 0;
        }
    }

    @Override
    public int checkUserByUserName(String acc) {
        ArrayList<Object> parameters = new ArrayList<>();
        parameters.add(acc);
        String sql = "select id from user where username=?";
        if(dbUtils.selectData(sql,parameters,User.class).size() > 0){
            return 0;
        }else{
            return 1;
        }
    }

    @Override
    public ArrayList<User> getAllUser() {
        String sql = "select * from user";
        ArrayList<User> users = dbUtils.selectData(sql,null,User.class);
        return users;
    }
}
