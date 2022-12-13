package com.dao;

import model.User;

import java.util.ArrayList;

public interface UserDao {
    /**
     * 登录，返回user列表
     * @param acc 用户名
     * @param pwd 密码
     * @return ArrayList
     */
    ArrayList<User> getUserByIdAndPwd(String acc,String pwd);

    /**
     * 注册用户
     * @param user user类实体
     * @return boolean
     */
    int insertUser(User user);

    /**
     * 判断用户账号是否重复
     * @param acc 账号
     * @return int
     */
    int checkUserByUserName(String acc);

    /**
     * 获取所有用户，不管在不在线
     * @return list
     */
    ArrayList<User> getAllUser();
}
