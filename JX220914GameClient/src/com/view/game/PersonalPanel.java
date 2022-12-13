package com.view.game;

import com.commen.ImageData;
import com.controller.Controller;
import com.util.RoundBorder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PersonalPanel extends JPanel {
    private Controller con;
    private JPanel panel;
    private ImageIcon personIcon = new ImageIcon("");
    private ImageIcon hatIcon = new ImageIcon("");
    private ImageIcon clothesIcon = new ImageIcon("");

    public PersonalPanel(Controller con) {
        this.con = con;
        setLayout(null);

        JButton p1 = new JButton(ImageData.P1);
        p1.setBounds(80,98,65,65);
        p1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                personIcon = ImageData.BODYS.get(0);
                con.pFrame.repaint();
            }
        });
        JButton p2 = new JButton(ImageData.P2);
        p2.setBounds(160,98,65,65);
        p2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                personIcon = ImageData.BODYS.get(1);
                con.pFrame.repaint();
            }
        });
        JButton p3 = new JButton(ImageData.P3);
        p3.setBounds(240,98,65,65);
        p3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                personIcon = ImageData.BODYS.get(2);
                con.pFrame.repaint();
            }
        });
        JButton p4 = new JButton(ImageData.P4);
        p4.setBounds(320,98,65,65);
        p4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                personIcon = ImageData.BODYS.get(3);
                con.pFrame.repaint();
            }
        });
        JButton p5 = new JButton(ImageData.P5);
        p5.setBounds(400,98,65,65);
        p5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                personIcon = ImageData.BODYS.get(4);
                con.pFrame.repaint();
            }
        });
        JButton p6 = new JButton(ImageData.P6);
        p6.setBounds(480,98,65,65);
        p6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                personIcon = ImageData.BODYS.get(5);
                con.pFrame.repaint();
            }
        });
        JButton p7 = new JButton(ImageData.P7);
        p7.setBounds(560,98,65,65);
        p7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                personIcon = ImageData.BODYS.get(6);
                con.pFrame.repaint();
            }
        });
        JButton p8 = new JButton(ImageData.P8);
        p8.setBounds(640,98,65,65);
        p8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                personIcon = ImageData.BODYS.get(7);
                con.pFrame.repaint();
            }
        });
        p1.setBorder(null);p2.setBorder(null);p3.setBorder(null);p4.setBorder(null);
        p5.setBorder(null);p6.setBorder(null);p7.setBorder(null);p8.setBorder(null);
        p1.setContentAreaFilled(false);p2.setContentAreaFilled(false);
        p3.setContentAreaFilled(false);p4.setContentAreaFilled(false);
        p5.setContentAreaFilled(false);p6.setContentAreaFilled(false);
        p7.setContentAreaFilled(false);p8.setContentAreaFilled(false);
        add(p1);add(p2);add(p3);add(p4);
        add(p5);add(p6);add(p7);add(p8);
        JTable js = new JTable();
        js.setBackground(Color.white);
        js.setBorder(new RoundBorder());
        js.setBounds(50,90,680,80);
        add(js);

        panel = new JPanel();
        panel.setBounds(300,250,420,210);
        panel.setLayout(new GridLayout(2,4,5,5));
        for (ImageIcon head : ImageData.HATS) {
            JButton headBtn = new JButton(head);
            headBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    hatIcon = head;
                    repaint();
                }
            });
            headBtn.setBackground(new Color(255, 255, 220));
            panel.add(headBtn);
        }
        for (ImageIcon c : ImageData.CLOTHES) {
            JButton clotheBtn = new JButton(c);
            clotheBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    clothesIcon = c;
                    repaint();
                }
            });
            clotheBtn.setBackground(new Color(255, 255, 220));
            panel.add(clotheBtn);
        }
        add(panel);


        JButton exit = new JButton("返回大厅");
        exit.setBounds(300,600,120,40);
        exit.setFont(con.font);
        exit.setBackground(Color.white);
        exit.setBorder(null);
        exit.addActionListener(con.personAct);
        exit.setActionCommand("back");
        add(exit);

        JButton sure = new JButton("确 定");
        sure.setBounds(500,600,120,40);
        sure.setFont(con.font);
        sure.setBackground(Color.white);
        sure.setBorder(null);
        sure.setActionCommand("sure");
        sure.addActionListener(con.personAct);
        add(sure);

        JButton hatBtn = new JButton("自定义帽子");

        JButton clothesBtn = new JButton("自定义衣服");

    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(ImageData.PERSONAL.getImage(),0,0,800,700,null);
        g.drawImage(ImageData.PERPENAL.getImage(),-200,-150,1000,900,null);
        g.drawImage(personIcon.getImage(),80,200,210,300,null);
        g.drawImage(hatIcon.getImage(),115,200,150,100,null);
        g.drawImage(clothesIcon.getImage(),150,400,70,70,null);
    }

    public ImageIcon getPersonIcon() {
        return personIcon;
    }

    public void setPersonIcon(ImageIcon personIcon) {
        this.personIcon = personIcon;
    }

    public ImageIcon getHatIcon() {
        return hatIcon;
    }

    public void setHatIcon(ImageIcon hatIcon) {
        this.hatIcon = hatIcon;
    }

    public ImageIcon getClothesIcon() {
        return clothesIcon;
    }

    public void setClothesIcon(ImageIcon clothesIcon) {
        this.clothesIcon = clothesIcon;
    }

    public void changeShowImage(){
        personIcon = new ImageIcon(PersonalFrame.role.getImage());
        hatIcon = new ImageIcon(PersonalFrame.role.getHat());
        clothesIcon = new ImageIcon(PersonalFrame.role.getClothes());
    }
}
