package com.view.game;

import com.commen.ImageData;
import com.commen.MusicData;
import com.controller.Controller;
import com.controller.game.TreeAct;
import com.util.IconTreeCellRender;
import com.util.IconTreeNode;
import model.Room;
import model.User;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class GHallPanel extends JPanel {
    private Controller con;
    public static List<User> users = new ArrayList<>();// 已登录的用户
    private JTree jTree;
    private DefaultTreeModel treeModel;
    private JButton yinyue;
    private JButton yinxiao;
    public static boolean yueFlag;
    public static boolean xiaoFlag;

    public GHallPanel(Controller con) {
        this.con = con;
        setLayout(null);

        yueFlag= true;
        xiaoFlag = true;

        IconTreeNode root = new IconTreeNode(null,null,null);
        treeModel = new DefaultTreeModel(root);
        jTree = new JTree(treeModel);
        jTree.setRowHeight(60);// 设置行高
        jTree.setRootVisible(false);// 不显示父节点
        jTree.addMouseListener(con.treeAct);
        jTree.setCellRenderer(new IconTreeCellRender());// 更改渲染器
        JScrollPane jsp = new JScrollPane(jTree);
        jsp.setBounds(10,10,200,400);
        add(jsp);

        JButton room = new JButton("游 戏 房 间");
        room.setBounds(600,80,150,50);
        room.setBackground(Color.white);
        room.setBorder(null);
        room.setFont(con.font);
        room.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                con.ghFrame.setVisible(false);
                con.grFrame.grPanel.setPanel();
                for (Room room1 : GameRoomPanel.roomList) {
                    con.grFrame.grPanel.addRoom(room1);
                }
                con.grFrame.repaint();
                con.grFrame.setVisible(true);
            }
        });
        add(room);

        JButton explain = new JButton("游 戏 说 明");
        explain.setBounds(600,150,150,50);
        explain.setFont(con.font);
        explain.setBackground(Color.white);
        explain.setBorder(null);
        explain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"W/A/S/D：上下左右，空格：放炸弹");
            }
        });
        add(explain);

        JButton self = new JButton("个 人 中 心");
        self.setBounds(600,220,150,50);
        self.setFont(con.font);
        self.setBackground(Color.white);
        self.setBorder(null);
        self.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                con.ghFrame.setVisible(false);
                con.pFrame.setVisible(true);
            }
        });
        add(self);

        yinyue = new JButton();
        yinyue.setBounds(500,20,30,30);
        yinyue.setIcon(ImageData.CLOSEYINYUE);
        yinyue.setContentAreaFilled(false);// 设置为透明
        yinyue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                yueFlag = !yueFlag;
                if(yueFlag){
                    yinyue.setIcon(ImageData.YINYUE);
                    Random rd = new Random();
                    int a = rd.nextInt(2);
                    if(a == 0) {
                        MusicData.bgMusic01.play();
                    }else{
                        MusicData.bgMusic02.play();
                    }
                }else{
                    yinyue.setIcon(ImageData.CLOSEYINYUE);
                    MusicData.bgMusic01.over();
                    MusicData.bgMusic02.over();
                }
            }
        });
        add(yinyue);

        yinxiao = new JButton();
        yinxiao.setBounds(540,20,30,30);
        yinxiao.setIcon(ImageData.CLOSEYINXIAO);
        yinxiao.setContentAreaFilled(false);// 设置为透明
        yinxiao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xiaoFlag = !xiaoFlag;
                if(xiaoFlag){
                    yinxiao.setIcon(ImageData.YINXIAO);
                }else{
                    yinxiao.setIcon(ImageData.CLOSEYINXIAO);
                }
            }
        });
        add(yinxiao);
    }

    public void setTreeModels(){
        IconTreeNode root = new IconTreeNode(null,null,null);
        treeModel = new DefaultTreeModel(root);
        for (User u : users) {
            if(!u.getUsername().equals(con.ghFrame.getUser().getUsername())) {
                Random rd = new Random();
                int i = rd.nextInt(8)+1;
                IconTreeNode node = new IconTreeNode(new ImageIcon("resources\\image\\heart\\P"+i+".png"), u.getUsername(), u.getNickname());
                root.add(node);
            }
        }
        jTree.setModel(treeModel);
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(ImageData.BGLIGHT.getImage(),0,0,800,500,null);
    }

    public JButton getYinyue() {
        return yinyue;
    }

    public void setYinyue(JButton yinyue) {
        this.yinyue = yinyue;
    }

    public JButton getYinxiao() {
        return yinxiao;
    }

    public void setYinxiao(JButton yinxiao) {
        this.yinxiao = yinxiao;
    }

    public JTree getjTree() {
        return jTree;
    }

    public void setjTree(JTree jTree) {
        this.jTree = jTree;
    }
}
