package com.view.welcome;

import com.controller.Controller;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {
    private Controller con;
    public LoginPanel lPanel;

    public LoginFrame(Controller con){
        this.con = con;
        lPanel = new LoginPanel(con);

        setSize(500,400);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);// 居中
        setTitle("登录界面-----JX220914-----吴子南");
        setResizable(false);// 无法更改大小
        addWindowListener(con.winAct);

        add(lPanel);
        setVisible(true);
    }
}
