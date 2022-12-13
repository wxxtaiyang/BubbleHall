package com.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.controller.Controller;
import com.dao.RoomDao;
import com.dao.impl.RoomDaoImpl;
import com.net.SocketLink;
import com.service.BusinessService;
import com.util.Action;
import model.Role;
import model.Room;

@Action("room")
public class RoomServiceImpl implements BusinessService {
    RoomDao roomDao = new RoomDaoImpl();
    @Override
    public void doBusinessService(JSONObject js, SocketLink link) {
        // -------------------------- 类型，创建房间 ----------------------
        if(js.getString("type").equals("create")) {
            Room room = js.getObject("room", Room.class);// 拿到房间
            roomDao.insertRoom(room);// 添加房间
            for (SocketLink value : Controller.LINKS.values()) { // 告知所有用户
                value.sendMessage(js.toString());
            }
            Controller.rooms.add(room);// 添加到服务端所有房间信息
        }//------------------------ 类型，加入 房间 -------------------------
        else if(js.getString("type").equals("join")){
            Room room = js.getObject("room",Room.class);// 拿到房间
            Role role = js.getObject("role", Role.class);// 拿到角色
            roomDao.joinRoom(room);// 修改数据库
            js.put("type","change");// 修改类型，房间信息改变
            for (String s : Controller.LINKS.keySet()) {// 告知所有人
                Controller.LINKS.get(s).sendMessage(js.toString());
            }
            JSONObject jsp = new JSONObject();
            jsp.put("action","room");// 事件，房间
            jsp.put("type","map");// 类型，获取地图
            jsp.put("room",room);// 房间信息
            jsp.put("role",role);// 进入房间的角色
            for (String s : Controller.LINKS.keySet()) {
                if(room.getMid().equals(s)){// 告知创建者
                    Controller.LINKS.get(s).sendMessage(jsp.toString());
                    break;
                }
            }
        }// --------------------- 类型，拿到地图 ------------------------
        else if(js.getString("type").equals("map")){
            Room room = js.getObject("room",Room.class);// 房间信息
            Role role = js.getObject("role",Role.class);// 创建者角色信息
            JSONArray json = JSONObject.parseArray(js.getString("map"));
            int[][] map = json.toJavaObject(new TypeReference<int[][]>(){});// 地图信息
            JSONObject jsp = new JSONObject();
            jsp.put("action","room");
            jsp.put("type","join");
            jsp.put("map",map);
            jsp.put("P1",js.getBoolean("P1"));
            jsp.put("evel",js.getInteger("evel"));// 地图背景
            jsp.put("role",role);
            for (String s : Controller.LINKS.keySet()) {
                if(room.getAgainstB().equals(s)){// 告知加入者
                    Controller.LINKS.get(s).sendMessage(jsp.toString());
                    break;
                }
            }
        }// ----------------------- 类型，退出房间 ----------------------
        else if(js.getString("type").equals("exit")){
            Room room = js.getObject("room",Room.class);// 房间信息
            String userName = js.getString("user");// 退出者id
            if(room.getMid().equals(userName)){// 判断是不是创建者退出
                for (Room r : Controller.rooms) {
                    if(r.getMid().equals(room.getMid())){// 服务端拿到集合中的那个房间
                        Controller.rooms.remove(r);// 删除掉改房间
                        break;
                    }
                }
                JSONObject jsp = new JSONObject();
                jsp.put("action","room");
                jsp.put("type","del");
                jsp.put("room",room);
                for (String s : Controller.LINKS.keySet()) {// 告知所有人房间被删除
                    Controller.LINKS.get(s).sendMessage(jsp.toString());
                }
                // 修改数据库，删除该房间
                roomDao.deleteRoom(room);

            }else {
                for (String s : Controller.LINKS.keySet()) {
                    if(s.equals(room.getAgainstA()) || s.equals(room.getAgainstB())){// 告知创建者和加入者
                        Controller.LINKS.get(s).sendMessage(js.toString());// 用户退出
                    }
                }
                room.setAgainstB("");
                room.setWinner("等待");// 修改房间信息
                // 数据库修改
                roomDao.joinRoom(room);
                JSONObject jsp = new JSONObject();
                jsp.put("action","room");// 事件，房间事件
                jsp.put("type","change");// 类型，有用户退出
                jsp.put("room",room);// 房间信息
                for (String s : Controller.LINKS.keySet()) {
                    Controller.LINKS.get(s).sendMessage(jsp.toString());// 告知所有用户房间信息改变
                }
            }

        }// -------------- 类型：准备开始 ---------------------
        else if(js.getString("type").equals("ready")){
            Room room = js.getObject("room",Room.class);// 拿到房间信息
            for (String s : Controller.LINKS.keySet()) {
                if(s.equals(room.getAgainstA()) || s.equals(room.getAgainstB())){// 将准备信息回执到房主和加入者
                    Controller.LINKS.get(s).sendMessage(js.toString());
                }
            }
        }
    }
}
