package com.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.controller.Controller;
import com.net.ClientLink;
import com.service.BusinessService;
import com.util.Action;
import com.view.game.GameRoomPanel;
import com.view.game.MapPanel;
import com.view.game.WaitGameFrame;
import model.Role;
import model.Room;

import javax.swing.*;

@Action("game")
public class GameServiceImpl implements BusinessService {

    private Controller con;
    @Override
    public void doBusinessService(JSONObject js, ClientLink link) {
        if(js.getString("type").equals("move")) {
            if(con.wgFrame.wgPanel.getPlay1() != null ) {
                con.wgFrame.wgPanel.getPlay1().setPointX(js.getInteger("play1X"));
                con.wgFrame.wgPanel.getPlay1().setPointY(js.getInteger("play1Y"));
            }
            if(con.wgFrame.wgPanel.getPlay2() != null){
                con.wgFrame.wgPanel.getPlay2().setPointX(js.getInteger("play2X"));
                con.wgFrame.wgPanel.getPlay2().setPointY(js.getInteger("play2Y"));
            }
            con.mFrame.repaint();
        }else if(js.getString("type").equals("map")) {
            JSONArray json = JSONObject.parseArray(js.getString("map"));
            MapPanel.map = json.toJavaObject(new TypeReference<int[][]>() {});
            con.mFrame.repaint();
        }
        else if(js.getString("type").equals("bombNum")){
            con.wgFrame.wgPanel.getPlay1().setBomNum(js.getInteger("1"));
            con.wgFrame.wgPanel.getPlay2().setBomNum(js.getInteger("2"));
            con.mFrame.mpPanel.getP11().setText(": "+con.wgFrame.wgPanel.getPlay1().getBomNum());
            con.mFrame.mpPanel.getP21().setText(": "+con.wgFrame.wgPanel.getPlay2().getBomNum());
            con.mFrame.repaint();
        }else if(js.getString("type").equals("over")){
            System.out.println(js);
            int p1 = js.getInteger("1");
            int p2 = js.getInteger("2");
            Room room = js.getObject("room",Room.class);
            System.out.println(room);
            MapPanel.flag = false;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(con.ghFrame.getUser().getUsername().equals(GameRoomPanel.room.getAgainstA()) && p1 == 0){
                JOptionPane.showMessageDialog(null,"你输了");
                gameOver();
            }else if(con.ghFrame.getUser().getUsername().equals(GameRoomPanel.room.getAgainstA()) && p2 == 0){
                JOptionPane.showMessageDialog(null,"你赢了");
                gameOver();
            }else if(con.ghFrame.getUser().getUsername().equals(GameRoomPanel.room.getAgainstB()) && p1 == 0){
                JOptionPane.showMessageDialog(null,"你赢了");
                gameOver();
            }else if(con.ghFrame.getUser().getUsername().equals(GameRoomPanel.room.getAgainstB()) && p2 == 0){
                JOptionPane.showMessageDialog(null,"你输了");
                gameOver();
            }else if(js.getString("flag") != null){
                JOptionPane.showMessageDialog(null,"平局");
                gameOver();
            }
            for (int i = 0; i < GameRoomPanel.roomList.size(); i++) {
                if(GameRoomPanel.roomList.get(i).getMid().equals(room.getMid())){
                    GameRoomPanel.roomList.remove(GameRoomPanel.roomList.get(i));// 删除房间集合中的那个房间
                    con.grFrame.grPanel.removeRoom();// 删除房间界面中的那个房间
                    break;
                }
            }

        }

    }

    public void gameOver(){
        con.mFrame.setVisible(false);
        con.grFrame.setVisible(true);
        con.wgFrame.wgPanel.setPlay1(null);
        con.wgFrame.wgPanel.setPlay2(null);
        con.wgFrame = new WaitGameFrame(con);
        MapPanel.flag = true;
    }
}
