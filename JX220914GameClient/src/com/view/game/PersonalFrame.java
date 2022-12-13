package com.view.game;

import com.commen.ImageData;
import com.controller.Controller;
import model.Role;

import javax.swing.*;
import java.awt.*;

public class PersonalFrame extends JFrame {
    private Controller con;
    public static Role role = new Role(0,"","resources\\image\\role\\P1.png",
            "","");
    public PersonalPanel pPanel;

    public PersonalFrame(Controller con){
        this.con = con;

        setSize(800,700);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);// 居中
        setTitle("个人中心-----JX220914-----吴子南");
        setResizable(false);// 无法更改大小
        addWindowListener(con.winAct);

        pPanel= new PersonalPanel(con);
        add(pPanel);

//        setVisible(true);
    }


}
