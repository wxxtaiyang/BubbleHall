package com.controller.game;

import com.alibaba.fastjson.JSONObject;
import com.controller.Controller;
import com.util.IconTreeNode;
import com.view.game.GHallTake;
import model.User;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TreeAct extends MouseAdapter implements TreeSelectionListener {
    private Controller con;

    public TreeAct(Controller con) {
        this.con = con;
    }

    @Override
    public void valueChanged(TreeSelectionEvent e) {
        TreePath treePath = e.getPath();
//        System.out.println(treePath.getLastPathComponent() instanceof  DefaultMutableTreeNode);
        // 强转拿到点击的节点数据
        IconTreeNode node = (IconTreeNode) treePath.getLastPathComponent();
        System.out.println(node.getUserObject() instanceof User);

        GHallTake.recvId = node.getUserid();// 拿到账号 要私聊的那个账号
        if (GHallTake.history.get(node.getUserid()) == null) {
            JSONObject js = new JSONObject();
            js.put("action", "record");
            js.put("sendid", con.ghFrame.getUser().getUsername());
            js.put("recvid", node.getUserid());
            con.link.sendMessage(js.toString());
        } else {
            con.ghFrame.ghtake.setModels(GHallTake.recvId);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //获取鼠标选中TreePath
        TreePath selPath = con.ghFrame.ghPanel.getjTree().getPathForLocation(e.getX(), e.getY());
        if (null != selPath) {
            //将当前鼠标选中的节点设置为选中状态。
            con.ghFrame.ghPanel.getjTree().setSelectionPath(selPath);

            //获取该节点
            IconTreeNode node = (IconTreeNode) selPath.getLastPathComponent();
            // 强转拿到点击的节点数据
            System.out.println(node.getUserObject() instanceof User);

            GHallTake.recvId = node.getUserid();// 拿到账号 要私聊的那个账号
            if (GHallTake.history.get(node.getUserid()) == null) {
                JSONObject js = new JSONObject();
                js.put("action", "record");
                js.put("sendid", con.ghFrame.getUser().getUsername());
                js.put("recvid", node.getUserid());
                con.link.sendMessage(js.toString());
            } else {
                con.ghFrame.ghtake.setModels(GHallTake.recvId);
            }
        }
    }
}
