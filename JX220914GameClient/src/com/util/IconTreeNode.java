package com.util;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;

public class IconTreeNode extends DefaultMutableTreeNode {
    private ImageIcon image; // 图标
    private String userid;// id
    private String username;// 名称

    public IconTreeNode(ImageIcon image,String userid, String username) {
        this.image = image;

        this.userid = userid;
        this.username = username;
    }

    public IconTreeNode() {
    }

    public ImageIcon getImage() {
        return image;
    }

    public void setImage(ImageIcon image) {
        this.image = image;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    @Override
    public String toString() {
        return username;
    }
}
