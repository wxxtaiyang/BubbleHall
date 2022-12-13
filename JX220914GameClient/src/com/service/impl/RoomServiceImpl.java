package com.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.controller.Controller;
import com.net.ClientLink;
import com.service.BusinessService;
import com.util.Action;
import com.util.DownThread;
import com.view.game.GameRoomPanel;
import com.view.game.MapPanel;
import com.view.game.PersonalFrame;
import model.Role;
import model.Room;

import javax.swing.*;

@Action("room")
public class RoomServiceImpl implements BusinessService {
    private Controller con;
    @Override
    public void doBusinessService(JSONObject js, ClientLink link) {
        if(js.getString("type").equals("create")){// 创建房间
            con.grFrame.grPanel.addRoom(js.getObject("room", Room.class));// 添加游戏房间界面的房间按钮
            con.grFrame.grPanel.dialog.setVisible(false);// 关闭地图选择
            if(con.ghFrame.getUser().getUsername().equals(js.getObject("room", Room.class).getMid())){// 判断是否为创建者
                con.grFrame.setVisible(false);// 关闭游戏房间界面
                con.mFrame.repaint();// 重渲染地图
                con.wgFrame.wgPanel.setPlay1(PersonalFrame.role);// 设置准备界面P1的角色
                con.wgFrame.repaint();// 准备界面重新渲染
                con.wgFrame.setVisible(true);// 进入准备界面
                GameRoomPanel.room = js.getObject("room", Room.class);// 当前进入的房间修改
            }
            GameRoomPanel.roomList.add(js.getObject("room", Room.class));// 将房间添加到集合中
        }// ----------------- 类型：获取地图-----------------------
        else if(js.getString("type").equals("map")){
            Room room = js.getObject("room",Room.class);// 得到房间
            System.out.println(room.getAgainstB());
            GameRoomPanel.room = room;// 房间信息修改
            Role role = js.getObject("role",Role.class);// 得到加入者的角色
            con.wgFrame.wgPanel.setPlay2(role);// 设置角色到P2
            con.wgFrame.repaint();// 重新渲染
            for (int i = 0; i < GameRoomPanel.roomList.size(); i++) {
                if(GameRoomPanel.roomList.get(i).getMid().equals(room.getMid())){
                    GameRoomPanel.roomList.set(i,room);// 修改集合中房间信息
                    break;
                }
            }
            js.put("map", MapPanel.map);// 地图
            js.put("evel",MapPanel.mapEvel);// 地图下标
            js.put("role",PersonalFrame.role);// P1玩家信息
            js.put("P1",con.wgFrame.wgPanel.isP1Ready());
            link.sendMessage(js.toString());// 回执
        }//----------------- 类型：加入房间 -------------------------------------
        else if(js.getString("type").equals("join")){
            JSONArray json = JSONObject.parseArray(js.getString("map"));
            int[][] map = json.toJavaObject(new TypeReference<int[][]>(){});// 得到地图
            MapPanel.map = map;// 地图赋值
            MapPanel.mapEvel = js.getInteger("evel");// 地图下标赋值
            con.wgFrame.wgPanel.setP1Ready(js.getBoolean("P1"));
            con.grFrame.setVisible(false);// 关闭游戏房间界面
            con.wgFrame.wgPanel.setPlay1(js.getObject("role",Role.class));// 设置P1为创建者
            con.wgFrame.wgPanel.setPlay2(PersonalFrame.role);// 设置P2为自己
            con.wgFrame.repaint();// 重新渲染页面
            con.wgFrame.setVisible(true);// 打开准备房间界面
        }//--------------- 类型：改变房间 ---------------------------
        else if(js.getString("type").equals("change")){
            Room room = js.getObject("room",Room.class);
            for (int i = 0; i < GameRoomPanel.roomList.size(); i++) {
                if(GameRoomPanel.roomList.get(i).getMid().equals(room.getMid())){// 修改数组中的值
                    GameRoomPanel.roomList.set(i,room);
                    break;
                }
            }
            for (JButton roomBtn : con.grFrame.grPanel.getRoomBtns()) {// 修改界面button值
                String id = roomBtn.getText().split("-")[0];
                if(id.equals(room.getMid())){
                    roomBtn.setText(room.getMid()+"-"+room.getWinner());
                    break;
                }
            }
            con.grFrame.grPanel.getJsp().setViewportView(con.grFrame.grPanel.getPanel());// 刷新JScrop
        }// ---------------- 类型：删除房间 ---------------------------------------
        else if(js.getString("type").equals("del")){
            Room room = js.getObject("room",Room.class);
            System.out.println("删除房间");
            System.out.println(room.getAgainstB()+"--"+room.getAgainstA());
            if(con.ghFrame.getUser().getUsername().equals(room.getAgainstB())
                    || con.ghFrame.getUser().getUsername().equals(room.getAgainstA())){// 判断当前用户是否在房间里面
                con.wgFrame.setVisible(false);
                con.grFrame.setVisible(true);// 退出准备界面，进入房间列表界面
                GameRoomPanel.room = null;// 房间信息设空
                con.wgFrame.wgPanel.setPlay2(null);
                con.wgFrame.wgPanel.setPlay1(null);
                con.wgFrame.wgPanel.setP1Ready(false);
                con.wgFrame.wgPanel.setP2Ready(false);
            }
            for (int i = 0; i < GameRoomPanel.roomList.size(); i++) {
                if(GameRoomPanel.roomList.get(i).getMid().equals(room.getMid())){
                    GameRoomPanel.roomList.remove(GameRoomPanel.roomList.get(i));// 删除房间集合中的那个房间
                    con.grFrame.grPanel.removeRoom();// 删除房间界面中的那个房间
                    break;
                }
            }
        }// --------------- 类型：用户退出事件 ---------------------------
        else if(js.getString("type").equals("exit")){
            Room room = js.getObject("room",Room.class);
            if(room.getAgainstB().equals(con.ghFrame.getUser().getUsername())){// 判断当前用户是否是要退出用户
                con.wgFrame.wgPanel.setPlay2(null);
                con.wgFrame.wgPanel.setPlay1(null);
                con.wgFrame.wgPanel.setP1Ready(false);
                con.wgFrame.wgPanel.setP2Ready(false);
                con.wgFrame.setVisible(false);
                con.grFrame.setVisible(true);// 退出准备界面，进入房间列表界面
                GameRoomPanel.room = null;// 房间信息设空
            }
            if(room.getAgainstA().equals(con.ghFrame.getUser().getUsername())){// 判断当前是否为房主
                con.wgFrame.wgPanel.setPlay2(null);// 设置角色为空
                con.wgFrame.wgPanel.setP2Ready(false);
                con.wgFrame.repaint();// 重新渲染
            }
        }// -------------- 类型，用户准备事件 --------------------------
        else if(js.getString("type").equals("ready")){
            String name = js.getString("user");
            Room room = js.getObject("room",Room.class);
            boolean flag = js.getBoolean("flag");
            if(name.equals(room.getAgainstA())){
                con.wgFrame.wgPanel.setP1Ready(flag);
            }else if(name.equals(room.getAgainstB())){
                con.wgFrame.wgPanel.setP2Ready(flag);
            }
            con.wgFrame.repaint();
            DownThread downThread = new DownThread(con);
            downThread.start();
        }
    }
}
