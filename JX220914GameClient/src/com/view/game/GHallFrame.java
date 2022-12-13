package com.view.game;

import com.controller.Controller;
import model.User;

import javax.swing.*;
import java.awt.*;

public class GHallFrame extends JFrame {
    private Controller con;
    private User user;
    public GHallPanel ghPanel;
    public GHallTake ghtake;


    public GHallFrame(Controller con) {
        this.con = con;

        setLayout(new BorderLayout());
        setSize(800,700);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);// 居中
        setTitle("大厅界面-----JX220914-----吴子南");
        setResizable(false);// 无法更改大小
        addWindowListener(con.winAct);

        ghtake = new GHallTake(con);
        add(ghtake,BorderLayout.SOUTH);

        ghPanel = new GHallPanel(con);
        add(ghPanel);

//        setVisible(true);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
