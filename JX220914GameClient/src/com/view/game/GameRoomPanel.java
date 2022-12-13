package com.view.game;

import com.alibaba.fastjson.JSONObject;
import com.commen.ImageData;
import com.controller.Controller;
import model.Room;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class GameRoomPanel extends JPanel {
    private Controller con;
    private JPanel panel;
    private JScrollPane jsp;
    private List<JButton> roomBtns;// 所有房间按钮
    public static List<Room> roomList;// 所有可查看房间
    public MapDialog dialog;// 选择地图界面的dialog
    public static Room room;// 进入的当前房间

    public GameRoomPanel(Controller con) {
        this.con = con;
        setLayout(null);

        roomBtns = new ArrayList<>();
        roomList = new ArrayList<>();
        dialog = new MapDialog(con);

        JButton createRoom = new JButton("创 建 房 间");
        createRoom.setActionCommand("create");
        createRoom.setFont(con.font);
        createRoom.setBounds(10, 10, 200, 50);
        createRoom.setBackground(Color.white);
        createRoom.setBorder(null);
        createRoom.setActionCommand("create");
        createRoom.addActionListener(con.roomAct);
        add(createRoom);

        JButton speedStare = new JButton("快 速 开 始");
        speedStare.setFont(con.font);
        speedStare.setBounds(220, 10, 200, 50);
        speedStare.setBackground(Color.white);
        speedStare.setBorder(null);
        speedStare.setActionCommand("fast");
        speedStare.addActionListener(con.roomAct);
        add(speedStare);

        JButton exitBtn = new JButton("返 回 大 厅");
        exitBtn.setActionCommand("exit");
        exitBtn.setFont(con.font);
        exitBtn.setBounds(430, 10, 200, 50);
        exitBtn.setBackground(Color.white);
        exitBtn.setBorder(null);
        exitBtn.setActionCommand("exit");
        exitBtn.addActionListener(con.roomAct);
        add(exitBtn);

        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                g.drawImage(ImageData.ROOMBG1.getImage(), 0, 0, 800
                        , 800, null);
            }
        };
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));

        jsp = new JScrollPane();
        jsp.setViewportView(panel);
        jsp.setBounds(0, 80, 790, 585);
        add(jsp);
    }

    public void addRoom(Room room) {
        JButton btn = new JButton(room.getMid() + "-" + room.getWinner());
        btn.setFont(con.font);
        btn.setIcon(ImageData.ROOMICON);
        btn.setPreferredSize(new Dimension(370, 80));
        btn.setBackground(new Color(228, 228, 228));
        btn.setActionCommand(btn.getText());
        btn.addActionListener(con.joinRoomAct);
        panel.add(btn);
        roomBtns.add(btn);
        panel.setPreferredSize(new Dimension(760, (int) (Math.ceil(roomBtns.size() / 2.0) * 85)));
        jsp.setViewportView(panel);
    }

    public void removeRoom() {
        setPanel();
        for (Room room1 : GameRoomPanel.roomList) {
            con.grFrame.grPanel.addRoom(room1);
        }
        jsp.setViewportView(panel);
        System.out.println("删除成功");
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(ImageData.ROOMBG.getImage(), 0, 0, 800, 700, null);
    }

    public List<JButton> getRoomBtns() {
        return roomBtns;
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setPanel() {
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                g.drawImage(ImageData.ROOMBG1.getImage(), 0, 0, 800
                        , 800, null);
            }
        };
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
    }

    public JScrollPane getJsp() {
        return jsp;
    }

    public void setJsp(JScrollPane jsp) {
        this.jsp = jsp;
    }
}
