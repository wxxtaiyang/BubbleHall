package com.view.game;

import com.commen.IconData;
import com.commen.ImageData;
import com.controller.Controller;
import com.util.TextPanelUtil;
import model.Record;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GHallTake extends JPanel implements ListCellRenderer{
    private Controller con;
    private TextPanelUtil msg;
    private DefaultListModel listModel;
    private JList jlist;
    public static Map<String, List<Record>> history = new HashMap<>();// 了解记录
    public static String recvId = "all";// 当前聊天用户ID
    public GHallImage gHallImage;
    private JLabel pLabel;// 显示页数文本
    public int nowPage = 1;// 当前页
    public int allPage;// 全部页数
    public int pageSize = 5;// 每页显示大小

    public GHallTake(Controller con) {
        this.con = con;
        setLayout(null);
        setPreferredSize(new Dimension(800,200));

        listModel = new DefaultListModel();
        jlist = new JList(listModel);
        jlist.setEnabled(false);
        jlist.setCellRenderer(this);
        JScrollPane js = new JScrollPane();
        js.setViewportView(jlist);
        js.setBounds(10,5,770,155);
        add(js);

        JButton allTake = new JButton("群聊");
        allTake.setFont(con.font);
        allTake.setBounds(10,165,60,30);
        allTake.setBackground(Color.white);
        allTake.setBorder(null);
        allTake.setActionCommand("all");
        allTake.addActionListener(con.takeAct);
        add(allTake);

        JButton left = new JButton();
        left.setIcon(ImageData.LEFT);
        left.setBounds(75,165,30,30);
        left.setBackground(Color.white);
        left.setBorder(null);
        left.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(nowPage - 1 > 0){
                    nowPage--;
                    setModels(GHallTake.recvId);
                }
            }
        });
        add(left);

        pLabel = new JLabel("00/00");
        pLabel.setFont(con.font);
        pLabel.setBounds(110,165,60,30);
        add(pLabel);

        JButton right = new JButton();
        right.setIcon(ImageData.RIGHT);
        right.setBounds(165,165,30,30);
        right.setBackground(Color.white);
        right.setBorder(null);
        right.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(nowPage + 1 <= allPage){
                    nowPage++;
                    setModels(GHallTake.recvId);
                }
            }
        });
        add(right);

        msg = new TextPanelUtil();
        msg.setFont(con.pwdFont);
        msg.setBounds(200,165,480,30);
        add(msg);

        JButton expression = new JButton();
        expression.setIcon(IconData.icon1);
        expression.setBounds(685,165,30,30);
        expression.setActionCommand("expression");
        expression.addActionListener(con.takeAct);
        expression.setBackground(Color.white);
        expression.setBorder(null);
        add(expression);

        JButton send = new JButton("发送");
        send.setFont(con.font);
        send.setBackground(Color.white);
        send.setBorder(null);
        send.addActionListener(con.takeAct);
        send.setActionCommand("send");
        send.setBounds(720,165,60,30);
        add(send);

        gHallImage = new GHallImage(con);
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(ImageData.TAKE.getImage(),0,0,800,200,null);
    }

    public DefaultListModel getListModel() {
        return listModel;
    }

    public void setModels(String id) {

        listModel = new DefaultListModel();
        List<Record> records = history.get(id); // 根据id拿到记录集合

        allPage = (int) Math.ceil(records.size()*1.0/pageSize);
        pLabel.setText(nowPage+"/"+allPage);
        // 遍历所有消息记录
        for (int i = (nowPage-1)*pageSize,j=0; j<pageSize; i++,j++) {
            if(i == records.size())
                break;
            for (User user : Controller.allUser) {
                // 如果发送者id等于该id
                if(user.getUsername().equals(records.get(i).getSendId())){
                    listModel.addElement(user.getNickname()+"："+records.get(i).getContent()+" ----------> "+records.get(i).getRecTime());
                    break;// 结束用户遍历
                }
            }
        }

        jlist.setModel(listModel);
    }

    public TextPanelUtil getMsg() {
        return msg;
    }

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        JPanel panel = new JPanel();
        panel.setFont(con.font);
        // 左对齐，不写是默认居中
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));

        String msg = value.toString();
        String[] texts = msg.split("&\\^");
        for (int i = 0; i < texts.length; i++) {
            if(i%2 == 0){
                JLabel label = new JLabel(texts[i]);
                label.setFont(con.font);
                panel.add(label);
            }else{
                Icon icon = new ImageIcon("resources/expression/"+texts[i]+".gif");
                JLabel label = new JLabel(icon);
                label.setFont(con.font);
                panel.add(label);
            }
        }
        return panel;
    }
}
