package com.view.game;

import com.commen.IconData;
import com.commen.ImageData;
import com.controller.Controller;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;

// JDialog JFrame 同级组件
public class GHallImage extends JDialog {
    private Controller con;

    public GHallImage(Controller con) {
        this.con = con;
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setResizable(false);

        JScrollPane jsp = new JScrollPane();

        getContentPane().setLayout(new FlowLayout(FlowLayout.LEFT));
        getContentPane().setPreferredSize(new Dimension(200, 410));
        jsp.setBounds(this.getRootPane().getX() + 5, this.getRootPane().getY() + 5, 220, 165);

        try {
            Class clazz = Class.forName("com.commen.IconData");
            Field[] fields = clazz.getDeclaredFields();
//            System.out.println(fields.length);
            for (int i = 0; i < fields.length; i++) {
                Field f = fields[i];
                // 通过getName拿到名字，通过get（名字）拿到对应的值
                Icon icon = (ImageIcon)f.get(f.getName());
                JButton icon1 = new JButton(icon);
                icon1.setPreferredSize(new Dimension(25,25));
                icon1.setBorderPainted(false);
                icon1.setContentAreaFilled(false);
                icon1.setActionCommand(i+1+"");
                icon1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        con.ghFrame.ghtake.getMsg().insertIcon(icon);
//                        con.ghFrame.ghtake.getMsg().setText(con.ghFrame.ghtake.getMsg().getText()+"&^"+e.getActionCommand()+"&^");
                        con.ghFrame.ghtake.gHallImage.setVisible(false);
                    }
                });
                getContentPane().add(icon1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 滚动条设置显示的组件为ContentPanel
        jsp.setViewportView(this.getContentPane());
        this.getRootPane().add(jsp);
    }

    public void getPosition() {
        setBounds(con.ghFrame.getX() + 800, con.ghFrame.getY() + 450, 230, 200);
//        setVisible(true);
    }
}
