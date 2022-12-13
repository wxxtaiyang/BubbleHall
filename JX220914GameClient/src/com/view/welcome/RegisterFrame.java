package com.view.welcome;

import com.controller.Controller;

import javax.swing.*;

public class RegisterFrame extends JFrame {
    private Controller con;

    public RegisterPanel rPanel;

    public RegisterFrame(Controller con) {
        this.con = con;

        this.con = con;
        rPanel = new RegisterPanel(con);

        setSize(400,400);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);// 居中
        setTitle("注册界面-----JX220914-----吴子南");
        setResizable(false);// 无法更改大小
        addWindowListener(con.winAct);

        add(rPanel);
    }
}
