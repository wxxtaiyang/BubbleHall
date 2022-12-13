package com.util;

import com.alibaba.fastjson.JSONObject;
import com.commen.MusicData;
import com.controller.Controller;
import com.controller.game.GameKeyAct;
import com.view.game.GHallPanel;
import com.view.game.GameRoomPanel;
import com.view.game.MapPanel;

import java.awt.*;
import java.util.*;
import java.util.List;

public class BombThread extends Thread {
    private Controller con;
    private Point point;
    private int power;
    private List<Point> bomb = new ArrayList<>();

    public BombThread(Controller con, Point point,int power) {
        this.con = con;
        this.point = point;
        this.power = power;
    }

    @Override
    public void run() {
        int time = 0;
        while (time <= 5000){
            if(time == 4000){
                powersList(point);
                if(GHallPanel.xiaoFlag){
                    MusicData.bom.play();
                }
            }
            if(time == 5000){
                for (Point point1 : bomb) {
                    MapPanel.map[point1.x][point1.y] = 0;
                }
                JSONObject js = new JSONObject();
                js.put("action", "game");
                js.put("type","map");
                js.put("room", GameRoomPanel.room);
                js.put("map", MapPanel.map);
                con.link.sendMessage(js.toString());
                GameKeyAct.num = 1;
            }
            time++;
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // 炸弹爆炸效果
    public void powersList(Point point){
        MapPanel.map[point.x][point.y] = 21;
        bomb.add(point);
        Random rd = new Random();
        for (int i = 1; i <= power; i++) {// 往左
            if(point.x - i < 0){
                break;
            }
            if(MapPanel.map[point.x - i][point.y] == 0 || MapPanel.map[point.x - i][point.y] > 30){
                Point p = new Point(point.x-i,point.y);
                MapPanel.map[point.x-i][point.y] = 21;
                bomb.add(p);
            }else if(MapPanel.map[point.x-i][point.y] == 1 || MapPanel.map[point.x-i][point.y] == 2){
                int a = rd.nextInt(10);
                if(a == 1){
                    MapPanel.map[point.x - i][point.y] = 31;
                }else if(a == 2){
                    MapPanel.map[point.x - i][point.y] = 32;
                }else if(a == 3){
                    MapPanel.map[point.x - i][point.y] = 33;
                }else if(a == 4){
                    MapPanel.map[point.x - i][point.y] = 34;
                }else {
                    MapPanel.map[point.x - i][point.y] = 0;
                }
                break;
            }else if(MapPanel.map[point.x-i][point.y] < 0){
                break;
            }
        }
        for (int i = 1; i <= power; i++) {// 往右
            if(point.x+i > 14){
                break;
            }
            if(MapPanel.map[point.x+i][point.y] == 0 || MapPanel.map[point.x+i][point.y] > 30){
                Point p = new Point(point.x+i,point.y);
                MapPanel.map[point.x+i][point.y] = 21;
                bomb.add(p);
            }else if(MapPanel.map[point.x+i][point.y] == 1 || MapPanel.map[point.x+i][point.y] == 2){
                int a = rd.nextInt(10);
                if(a == 1){
                    MapPanel.map[point.x+i][point.y] = 31;
                }else if(a == 2){
                    MapPanel.map[point.x+i][point.y] = 32;
                }else if(a == 3){
                    MapPanel.map[point.x+i][point.y] = 33;
                }else if(a == 4){
                    MapPanel.map[point.x+i][point.y] = 34;
                }else {
                    MapPanel.map[point.x+i][point.y] = 0;
                }
                break;
            }else if(MapPanel.map[point.x+i][point.y] < 0){
                break;
            }
        }
        for (int i = 1; i <= power; i++) {// 往上i
            if(point.y-i < 0){
                break;
            }
            if(MapPanel.map[point.x][point.y-i] == 0 || MapPanel.map[point.x][point.y-i] > 30){
                Point p = new Point(point.x,point.y-i);
                MapPanel.map[point.x][point.y-i] = 21;
                bomb.add(p);
            }else if(MapPanel.map[point.x][point.y-i] == 1 || MapPanel.map[point.x][point.y-i] == 2){
                int a = rd.nextInt(10);
                if(a == 1){
                    MapPanel.map[point.x][point.y-i] = 31;
                }else if(a == 2){
                    MapPanel.map[point.x][point.y-i] = 32;
                }else if(a == 3){
                    MapPanel.map[point.x][point.y-i] = 33;
                }else if(a == 4){
                    MapPanel.map[point.x][point.y-i] = 34;
                }else {
                    MapPanel.map[point.x][point.y-i] = 0;
                }
                break;
            }else if(MapPanel.map[point.x][point.y-i] < 0){
                break;
            }
        }
        for (int i = 1; i <= power; i++) {// 往下i
            if(point.y+i > 14){
                break;
            }
            if(MapPanel.map[point.x][point.y+i] == 0 || MapPanel.map[point.x][point.y+i] > 30){
                Point p = new Point(point.x,point.y+i);
                MapPanel.map[point.x][point.y+i] = 21;
                bomb.add(p);
            }else if(MapPanel.map[point.x][point.y+i] == 1 || MapPanel.map[point.x][point.y+i] == 2){
                int a = rd.nextInt(10);
                if(a == 1){
                    MapPanel.map[point.x][point.y+i] = 31;
                }else if(a == 2){
                    MapPanel.map[point.x][point.y+i] = 32;
                }else if(a == 3){
                    MapPanel.map[point.x][point.y+i] = 33;
                }else if(a == 4){
                    MapPanel.map[point.x][point.y+i] = 34;
                }else {
                    MapPanel.map[point.x][point.y+i] = 0;
                }
                break;
            }else if(MapPanel.map[point.x][point.y+i] < 0){
                break;
            }
        }
        JSONObject js = new JSONObject();
        js.put("action", "game");
        js.put("type","map");
        js.put("room", GameRoomPanel.room);
        js.put("map", MapPanel.map);
        con.link.sendMessage(js.toString());
    }
}
