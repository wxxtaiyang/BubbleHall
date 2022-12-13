package com.view.game;

import com.controller.Controller;

import javax.swing.*;
import java.awt.*;

public class MapFrame extends JFrame {
    private Controller con;
    public MapPanel mPanel;
    public MapPlayerPanel mpPanel;
    public MapFrame(Controller con){
        this.con = con;
        setLayout(new BorderLayout());
        setSize(820,650);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);// 居中
        setTitle("主界面-----JX220914-----吴子南");
        setResizable(false);// 无法更改大小
        addWindowListener(con.winAct);



        mPanel = new MapPanel(con);
        add(mPanel);
        mpPanel = new MapPlayerPanel(con);
        add(mpPanel,BorderLayout.WEST);

        addKeyListener(con.gameKeyAct);
//        setVisible(true);
    }
}
