package com.view.welcome;

import com.commen.ImageData;
import com.controller.Controller;

import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {
    private Controller con;
    private JTextField acc;// 账号
    private JPasswordField pwd;// 密码

    public LoginPanel(Controller con) {
        this.con = con;
        setLayout(null);

        JLabel accLabel = new JLabel("账 号：");
        accLabel.setFont(con.font);
        accLabel.setForeground(Color.WHITE);
        accLabel.setBounds(130,100,80,30);
        add(accLabel);
        acc = new JTextField();
        acc.setFont(con.pwdFont);
        acc.setBounds(200,100,200,30);
        add(acc);

        JLabel pwdJLabel = new JLabel("密 码：");
        pwdJLabel.setFont(con.font);
        pwdJLabel.setForeground(Color.WHITE);
        pwdJLabel.setBounds(130,180,80,30);
        add(pwdJLabel);
        pwd = new JPasswordField();
        pwd.setFont(con.pwdFont);
        pwd.setBounds(200,180,200,30);
        add(pwd);

        JButton btnLogin = new JButton("登 录");
        btnLogin.setFont(con.font);
        btnLogin.setBounds(130,260,100,30);
        btnLogin.setActionCommand("login");
        btnLogin.addActionListener(con.loginAct);
        btnLogin.setBackground(Color.white);
        btnLogin.setBorder(null);
        add(btnLogin);

        JButton btnReg = new JButton("注 册");
        btnReg.setFont(con.font);
        btnReg.setBounds(270,260,100,30);
        btnReg.setActionCommand("register");
        btnReg.addActionListener(con.loginAct);
        btnReg.setBackground(Color.white);
        btnReg.setBorder(null);
        add(btnReg);
    }

    public JTextField getAcc() {
        return acc;
    }

    public void setAcc(JTextField acc) {
        this.acc = acc;
    }

    public JPasswordField getPwd() {
        return pwd;
    }

    public void setPwd(JPasswordField pwd) {
        this.pwd = pwd;
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(ImageData.LOGIN.getImage(),0,0,500,400,null);
    }
}
