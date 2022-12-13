package com.view.game;

import com.controller.Controller;

import javax.swing.*;

public class GameRoomFrame extends JFrame {
    private Controller con;
    public GameRoomPanel grPanel;

    public GameRoomFrame(Controller con){
        this.con = con;

        setSize(800,700);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);// 居中
        setTitle("游戏房间-----JX220914-----吴子南");
        setResizable(false);// 无法更改大小
        addWindowListener(con.winAct);

        grPanel = new GameRoomPanel(con);
        add(grPanel);

//        setVisible(true);
    }
}
