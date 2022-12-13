package com.view.game;

import com.controller.Controller;

import javax.swing.*;

public class WaitGameFrame extends JFrame {
    private Controller con;
    public WaitGamePanel wgPanel;

    public WaitGameFrame(Controller con){
        this.con = con;

        setSize(800,640);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);// 居中
        setTitle("准备房间-----JX220914-----吴子南");
        setResizable(false);// 无法更改大小
        addWindowListener(con.winAct);

        wgPanel = new WaitGamePanel(con);
        add(wgPanel);

//        setVisible(true);
    }
}
