package com.view.game;

import com.commen.ImageData;
import com.controller.Controller;
import model.Role;

import javax.swing.*;
import java.awt.*;

public class WaitGamePanel extends JPanel {
    private Controller con;
    private Role play1;
    private Role play2;
    private boolean P1Ready;
    private boolean P2Ready;
    private int countDown = 0;

    public WaitGamePanel(Controller con) {
        this.con = con;
        setLayout(null);
        P1Ready = false;
        P2Ready = false;

        JButton exit = new JButton("退  出");
        exit.setBorder(null);
        exit.setBackground(Color.white);
        exit.setFont(new Font("华文琥珀", Font.PLAIN, 30));
        exit.setBounds(315,15,150,40);
        exit.setActionCommand("exit");
        exit.addActionListener(con.waitAct);
        add(exit);

        JButton btn = new JButton();
        btn.setBounds(315,60,150,110);
        btn.setContentAreaFilled(false);
        btn.addActionListener(con.waitAct);
        btn.setActionCommand("ready");
        add(btn);

    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(ImageData.WAITBG.getImage(),0,0,800,600,null);
        if(play1 != null) {
            g.drawImage(new ImageIcon(play1.getImage()).getImage(), 40, 250, 200, 300, null);
            g.drawImage(new ImageIcon(play1.getHat()).getImage(), 75, 250, 140, 100, null);
            g.drawImage(new ImageIcon(play1.getClothes()).getImage(), 105, 450, 70, 70, null);
        }
        if(play2 != null) {
            g.drawImage(new ImageIcon(play2.getImage()).getImage(), 560, 250, 200, 300, null);
            g.drawImage(new ImageIcon(play2.getHat()).getImage(), 595, 250, 140, 100, null);
            g.drawImage(new ImageIcon(play2.getClothes()).getImage(), 625, 450, 70, 70, null);
        }
        if(P1Ready){
            g.drawImage(ImageData.READY.getImage(),20,500,100,100,null);
        }
        if(P2Ready){
            g.drawImage(ImageData.READY.getImage(),540,500,100,100,null);
        }
        if(P1Ready && P2Ready){
            g.drawImage(ImageData.TIMEDOWN[countDown].getImage(),350,250,100,100,null);
        }

    }

    public Role getPlay1() {
        return play1;
    }

    public void setPlay1(Role play1) {
        this.play1 = play1;
    }

    public Role getPlay2() {
        return play2;
    }

    public void setPlay2(Role play2) {
        this.play2 = play2;
    }

    public boolean isP1Ready() {
        return P1Ready;
    }

    public void setP1Ready(boolean p1Ready) {
        P1Ready = p1Ready;
    }

    public boolean isP2Ready() {
        return P2Ready;
    }

    public void setP2Ready(boolean p2Ready) {
        P2Ready = p2Ready;
    }

    public int getCountDown() {
        return countDown;
    }

    public void setCountDown(int countDown) {
        this.countDown = countDown;
    }
}
