package com.util;

import com.alibaba.fastjson.JSONObject;
import com.controller.Controller;
import com.view.game.GameRoomPanel;
import com.view.game.MapPanel;

import java.awt.*;

public class CheckThread extends Thread {
    private Controller con;

    public CheckThread(Controller con) {
        this.con = con;
    }

    @Override
    public void run() {
        while (MapPanel.flag){
            try {
                bombBoold();
            }catch (Exception e) {
                System.err.println("该线程该结束了");
            }
        }
        System.err.println("判断线程结束");
    }
    // 碰撞
    public void bombBoold() {
        Rectangle play1 = new Rectangle(con.wgFrame.wgPanel.getPlay1().getPointX(), con.wgFrame.wgPanel.getPlay1().getPointY(), 40, 40);
        Rectangle play2 = new Rectangle(con.wgFrame.wgPanel.getPlay2().getPointX(), con.wgFrame.wgPanel.getPlay2().getPointY(), 40, 40);
        for (int i = 0; i < MapPanel.map.length; i++) {
            for (int j = 0; j < MapPanel.map[i].length; j++) {
                Rectangle m = new Rectangle(i*40, j* 40,40,40);
                if(MapPanel.map[i][j] == 21) {
                    if(play1.intersects(m) && con.wgFrame.wgPanel.getPlay1().isFlag()){
                        con.wgFrame.wgPanel.getPlay1().setFlag(false);
                        con.wgFrame.wgPanel.getPlay1().setBlood(con.wgFrame.wgPanel.getPlay1().getBlood() - 1);
                        synchronized (con.wgFrame.wgPanel.getPlay1()) {
                            sendBlood();
                        }
                    }
                    if(play2.intersects(m) && con.wgFrame.wgPanel.getPlay2().isFlag()){
                        con.wgFrame.wgPanel.getPlay2().setFlag(false);
                        con.wgFrame.wgPanel.getPlay2().setBlood(con.wgFrame.wgPanel.getPlay2().getBlood() - 1);
                        synchronized (con.wgFrame.wgPanel.getPlay2()) {
                            sendBlood();
                        }
                    }
                }else if(MapPanel.map[i][j] == 31){
                    if(play1.intersects(m)){
                        con.wgFrame.wgPanel.getPlay1().setBomNum(con.wgFrame.wgPanel.getPlay1().getBomNum() + 1);
                        con.mFrame.mpPanel.getP11().setText(": "+con.wgFrame.wgPanel.getPlay1().getBomNum());
                        MapPanel.map[i][j] = 0;
                    }else if(play2.intersects(m)){
                        con.wgFrame.wgPanel.getPlay2().setBomNum(con.wgFrame.wgPanel.getPlay2().getBomNum() + 1);
                        con.mFrame.mpPanel.getP21().setText(": "+con.wgFrame.wgPanel.getPlay2().getBomNum());
                        MapPanel.map[i][j] = 0;
                    }
                }else if(MapPanel.map[i][j] == 32){
                    if(play1.intersects(m)){
                        con.wgFrame.wgPanel.getPlay1().setPower(con.wgFrame.wgPanel.getPlay1().getPower() + 1);
                        MapPanel.map[i][j] = 0;
                    }else if(play2.intersects(m)){
                        con.wgFrame.wgPanel.getPlay2().setPower(con.wgFrame.wgPanel.getPlay2().getPower() + 1);
                        MapPanel.map[i][j] = 0;
                    }
                }else if(MapPanel.map[i][j] == 33){
                    if(play1.intersects(m)){
                        con.wgFrame.wgPanel.getPlay1().setBlood(con.wgFrame.wgPanel.getPlay1().getBlood() + 1);
                        MapPanel.map[i][j] = 0;
                        synchronized (con.wgFrame.wgPanel.getPlay1()) {
                            sendBlood();
                        }
                    }else if(play2.intersects(m)){
                        con.wgFrame.wgPanel.getPlay2().setBlood(con.wgFrame.wgPanel.getPlay2().getBlood() + 1);
                        MapPanel.map[i][j] = 0;
                        synchronized (con.wgFrame.wgPanel.getPlay2()) {
                            sendBlood();
                        }
                    }
                }else if(MapPanel.map[i][j] == 34){
                    if(play1.intersects(m)){
                        con.wgFrame.wgPanel.getPlay1().setSpeed(4);
                        MapPanel.map[i][j] = 0;
                    }else if(play2.intersects(m)){
                        con.wgFrame.wgPanel.getPlay2().setSpeed(4);
                        MapPanel.map[i][j] = 0;
                    }
                }
            }

        }


        con.mFrame.mpPanel.getP12().setText(": "+con.wgFrame.wgPanel.getPlay1().getSpeed());
        con.mFrame.mpPanel.getP13().setText(": "+con.wgFrame.wgPanel.getPlay1().getPower());
        con.mFrame.mpPanel.getP14().setText(": "+con.wgFrame.wgPanel.getPlay1().getBlood());

        con.mFrame.mpPanel.getP22().setText(": "+con.wgFrame.wgPanel.getPlay2().getSpeed());
        con.mFrame.mpPanel.getP23().setText(": "+con.wgFrame.wgPanel.getPlay2().getPower());
        con.mFrame.mpPanel.getP24().setText(": "+con.wgFrame.wgPanel.getPlay2().getBlood());

        con.mFrame.repaint();

    }

    public void sendBlood(){
        JSONObject js = new JSONObject();
        js.put("action","game");
        js.put("room", GameRoomPanel.room);
        js.put("type","blood");
        js.put("1",con.wgFrame.wgPanel.getPlay1().getBlood());
        js.put("2",con.wgFrame.wgPanel.getPlay2().getBlood());
        con.link.sendMessage(js.toString());
    }
}
