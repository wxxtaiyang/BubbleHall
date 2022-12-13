package com.view.welcome;

import com.commen.ImageData;
import com.controller.Controller;

import javax.swing.*;
import java.awt.*;

public class RegisterPanel extends JPanel {
    private Controller con;

    private JTextField acc;
    private JTextField uname;
    private JPasswordField pwd;
    private JPasswordField checkPwd;

    public RegisterPanel(Controller con) {
        this.con = con;
        setLayout(null);

        JLabel accLabel = new JLabel("账 号：");
        accLabel.setBounds(50,20,100,30);
        accLabel.setFont(con.font);
        accLabel.setForeground(Color.WHITE);
        add(accLabel);
        acc = new JTextField();
        acc.setBounds(150,20,150,30);
        acc.setFont(con.pwdFont);
        add(acc);

        JLabel nameLabel = new JLabel("名 称：");
        nameLabel.setBounds(50,80,100,30);
        nameLabel.setFont(con.font);
        nameLabel.setForeground(Color.WHITE);
        add(nameLabel);
        uname = new JTextField();
        uname.setBounds(150,80,150,30);
        uname.setFont(con.pwdFont);
        add(uname);

        JLabel pwdLabel = new JLabel("密 码：");
        pwdLabel.setBounds(50,140,100,30);
        pwdLabel.setFont(con.font);
        pwdLabel.setForeground(Color.WHITE);
        add(pwdLabel);
        pwd = new JPasswordField();
        pwd.setBounds(150,140,150,30);
        pwd.setFont(con.pwdFont);
        add(pwd);

        JLabel checkLabel = new JLabel("确认密码：");
        checkLabel.setBounds(50,200,100,30);
        checkLabel.setFont(con.font);
        checkLabel.setForeground(Color.WHITE);
        add(checkLabel);
        checkPwd = new JPasswordField();
        checkPwd.setBounds(150,200,150,30);
        checkPwd.setFont(con.pwdFont);
        add(checkPwd);

        JButton regBtn = new JButton("注 册");
        regBtn.addActionListener(con.regAct);
        regBtn.setActionCommand("register");
        regBtn.setBounds(50,260,80,30);
        regBtn.setFont(con.font);
        regBtn.setBackground(Color.white);
        regBtn.setBorder(null);
        add(regBtn);

        JButton logBtn = new JButton("登 录");
        logBtn.addActionListener(con.regAct);
        logBtn.setActionCommand("login");
        logBtn.setBounds(200,260,80,30);
        logBtn.setFont(con.font);
        logBtn.setBackground(Color.white);
        logBtn.setBorder(null);
        add(logBtn);
    }

    public JTextField getAcc() {
        return acc;
    }

    public void setAcc(JTextField acc) {
        this.acc = acc;
    }

    public JTextField getUname() {
        return uname;
    }

    public void setUname(JTextField uname) {
        this.uname = uname;
    }

    public JPasswordField getPwd() {
        return pwd;
    }

    public void setPwd(JPasswordField pwd) {
        this.pwd = pwd;
    }

    public JPasswordField getCheckPwd() {
        return checkPwd;
    }

    public void setCheckPwd(JPasswordField checkPwd) {
        this.checkPwd = checkPwd;
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(ImageData.REGISTER.getImage(),0,0,400,400,null);
    }
}
