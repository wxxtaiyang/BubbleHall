package com.controller.game;

import com.alibaba.fastjson.JSONObject;
import com.commen.MusicData;
import com.controller.Controller;
import com.util.BombThread;
import com.view.game.*;
import model.Role;
import model.Room;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class GameKeyAct extends KeyAdapter implements Runnable {
    private Controller con;
    private String direction;
    private int power;
    private Point point;// 放炸弹的坐标
    public static int num = 1;
    private int p1Time = 0;
    private int p2Time = 0;

    public GameKeyAct(Controller con) {
        this.con = con;
        direction = "";
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            direction = "W";
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            direction = "S";
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            direction = "A";
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            direction = "D";
        }else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if(num == 0){
                return;
            }
            num = 0;
            if(con.ghFrame.getUser().getUsername().equals(GameRoomPanel.room.getAgainstA())
                    && con.wgFrame.wgPanel.getPlay1().getBomNum() != 0) {
                point = new Point(con.wgFrame.wgPanel.getPlay1().getPointX() / 40, con.wgFrame.wgPanel.getPlay1().getPointY() / 40);
                power = con.wgFrame.wgPanel.getPlay1().getPower();
                con.wgFrame.wgPanel.getPlay1().setBomNum(con.wgFrame.wgPanel.getPlay1().getBomNum() - 1);
                synchronized (con.wgFrame.wgPanel.getPlay1()) {
                    if(GHallPanel.xiaoFlag){
                        MusicData.click.play();
                    }
                    sendBombNum();
                }
            }
            if(con.ghFrame.getUser().getUsername().equals(GameRoomPanel.room.getAgainstB())
                    && con.wgFrame.wgPanel.getPlay2().getBomNum() != 0){
                point = new Point(con.wgFrame.wgPanel.getPlay2().getPointX() / 40, con.wgFrame.wgPanel.getPlay2().getPointY() / 40);
                power = con.wgFrame.wgPanel.getPlay2().getPower();
                con.wgFrame.wgPanel.getPlay2().setBomNum(con.wgFrame.wgPanel.getPlay2().getBomNum() - 1);
                synchronized (con.wgFrame.wgPanel.getPlay2()) {
                    if(GHallPanel.xiaoFlag){
                        MusicData.click.play();
                    }
                    sendBombNum();
                }
            }
            BombThread bt = new BombThread(con,point,power);
            bt.start();
            MapPanel.map[point.x][point.y] = 10;
            JSONObject js = new JSONObject();
            js.put("action", "game");
            js.put("type","map");
            js.put("room", GameRoomPanel.room);
            js.put("map", MapPanel.map);
            con.link.sendMessage(js.toString());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        direction = "";
    }


    @Override
    public void run() {
        int num = 180;
        con.mFrame.mpPanel.getTime().setText(""+num);
        int time = 0;
        while (true) {
            if(num == 0){
                JSONObject js = new JSONObject();
                System.out.println("时间到");
                js.put("action","game");
                js.put("room", GameRoomPanel.room);
                js.put("type","blood");
                js.put("1",con.wgFrame.wgPanel.getPlay1().getBlood());
                js.put("2",con.wgFrame.wgPanel.getPlay2().getBlood());
                js.put("flag","123");
                con.link.sendMessage(js.toString());
                break;
            }
            if(time == 200){
                num--;
                con.mFrame.mpPanel.getTime().setText(""+num);
                time = 0;
            }
            time++;
            if(!con.wgFrame.wgPanel.getPlay1().isFlag()){
                if(p1Time == 400){
                    con.wgFrame.wgPanel.getPlay1().setFlag(true);
                    p1Time = 0;
                }else
                    p1Time++;
            }
            if(!con.wgFrame.wgPanel.getPlay2().isFlag()){
                if(p2Time == 400){
                    con.wgFrame.wgPanel.getPlay2().setFlag(true);
                    p2Time = 0;
                }else
                    p2Time++;
            }

            JSONObject js = new JSONObject();
            if(con.ghFrame.getUser().getUsername().equals(GameRoomPanel.room.getAgainstA())) {
                con.wgFrame.wgPanel.getPlay1().roleMove(direction, MapPanel.map);
            } else if(con.ghFrame.getUser().getUsername().equals(GameRoomPanel.room.getAgainstB())){
                con.wgFrame.wgPanel.getPlay2().roleMove(direction, MapPanel.map);
            }
            js.put("action", "game");
            js.put("room", GameRoomPanel.room);
            js.put("type","move");
            js.put("play1X", con.wgFrame.wgPanel.getPlay1().getPointX());
            js.put("play1Y", con.wgFrame.wgPanel.getPlay1().getPointY());
            js.put("play2X", con.wgFrame.wgPanel.getPlay2().getPointX());
            js.put("play2Y", con.wgFrame.wgPanel.getPlay2().getPointY());
            con.link.sendMessage(js.toString());
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("线程结束");
    }

    public void sendBombNum(){
        JSONObject js = new JSONObject();
        js.put("action","game");
        js.put("room", GameRoomPanel.room);
        js.put("type","bombNum");
        js.put("1",con.wgFrame.wgPanel.getPlay1().getBomNum());
        js.put("2",con.wgFrame.wgPanel.getPlay2().getBomNum());
        con.link.sendMessage(js.toString());
    }
}
