package com.view.game;

import com.commen.ImageData;
import com.controller.Controller;

import javax.swing.*;
import java.awt.*;

public class MapPlayerPanel extends JPanel {
    private Controller con;
    private JLabel P11;
    private JLabel P12;
    private JLabel P13;
    private JLabel P14;
    private JLabel P21;
    private JLabel P22;
    private JLabel P23;
    private JLabel P24;

    private JButton time;

    public MapPlayerPanel(Controller con) {
        this.con = con;
        setPreferredSize(new Dimension(200,600));
        setLayout(null);
        Font font = new Font("宋体",Font.BOLD,18);

        P11 = new JLabel(": 20");P12 = new JLabel(": 20");
        P13 = new JLabel(": 20");P14 = new JLabel(": 20");
        P11.setIcon(ImageData.BOMB);P12.setIcon(ImageData.SHORT);
        P13.setIcon(ImageData.POWER);P14.setIcon(ImageData.BOOLD);
        P11.setBounds(120,105,100,20);
        P12.setBounds(120,130,100,20);
        P13.setBounds(120,155,100,20);
        P14.setBounds(120,180,100,20);
        P11.setBackground(Color.white);P12.setBackground(Color.white);
        P13.setBackground(Color.white);P14.setBackground(Color.white);
        add(P11);add(P12);add(P13);add(P14);

        P21 = new JLabel(": 20");P22 = new JLabel(": 20");
        P23 = new JLabel(": 20");P24 = new JLabel(": 20");
        P21.setIcon(ImageData.BOMB);P22.setIcon(ImageData.SHORT);
        P23.setIcon(ImageData.POWER);P24.setIcon(ImageData.BOOLD);
        P21.setBounds(120,225,100,20);
        P22.setBounds(120,250,100,20);
        P23.setBounds(120,275,100,20);
        P24.setBounds(120,300,100,20);
        P21.setBackground(Color.white);P22.setBackground(Color.white);
        P23.setBackground(Color.white);P24.setBackground(Color.white);
        add(P21);add(P22);add(P23);add(P24);

        P11.setFont(font);P12.setFont(font);P13.setFont(font);P14.setFont(font);
        P21.setFont(font);P22.setFont(font);P23.setFont(font);P24.setFont(font);

        time = new JButton();
        time.setFont(con.font);
        time.setBounds(65,500,80,50);
        time.setEnabled(false);
        add(time);
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(ImageData.GAMEPLAYER.getImage(),0,0,200,600,null);

        if(con.wgFrame.wgPanel.getPlay1() != null){
            String path = con.wgFrame.wgPanel.getPlay1().getImage().replaceAll("role","heart");
            g.drawImage(new ImageIcon(path).getImage(),5,100,100,100,null);
        }
        if(con.wgFrame.wgPanel.getPlay2() != null){
            String path = con.wgFrame.wgPanel.getPlay2().getImage().replaceAll("role","heart");
            g.drawImage(new ImageIcon(path).getImage(),5,220,100,100,null);
        }
    }

    public JLabel getP11() {
        return P11;
    }

    public void setP11(JLabel p11) {
        P11 = p11;
    }

    public JLabel getP12() {
        return P12;
    }

    public void setP12(JLabel p12) {
        P12 = p12;
    }

    public JLabel getP13() {
        return P13;
    }

    public void setP13(JLabel p13) {
        P13 = p13;
    }

    public JLabel getP14() {
        return P14;
    }

    public void setP14(JLabel p14) {
        P14 = p14;
    }

    public JLabel getP21() {
        return P21;
    }

    public void setP21(JLabel p21) {
        P21 = p21;
    }

    public JLabel getP22() {
        return P22;
    }

    public void setP22(JLabel p22) {
        P22 = p22;
    }

    public JLabel getP23() {
        return P23;
    }

    public void setP23(JLabel p23) {
        P23 = p23;
    }

    public JLabel getP24() {
        return P24;
    }

    public void setP24(JLabel p24) {
        P24 = p24;
    }

    public JButton getTime() {
        return time;
    }

    public void setTime(JButton time) {
        this.time = time;
    }
}
