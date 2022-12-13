package com.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.controller.Controller;
import com.dao.BattleProcessDao;
import com.dao.RoomDao;
import com.dao.impl.BattleProcessDaoImpl;
import com.dao.impl.RoomDaoImpl;
import com.net.SocketLink;
import com.service.BusinessService;
import com.util.Action;
import model.Room;

@Action("game")
public class GameServiceImpl implements BusinessService {
    RoomDao roomDao = new RoomDaoImpl();
    BattleProcessDao bpDao = new BattleProcessDaoImpl();

    @Override
    public void doBusinessService(JSONObject js, SocketLink link) {
        for (String s : Controller.LINKS.keySet()) {
            if(s.equals(link.getUser().getUsername())){
                sendOther(js);
                break;
            }
        }
    }

    public void sendOther(JSONObject js){
        Room room = js.getObject("room",Room.class);
        if(js.getString("type").equals("blood")){
            int p1 = js.getInteger("1");
            int p2 = js.getInteger("2");
            Room r = js.getObject("room",Room.class);
            if(p1 == 0||p2 == 0){
                roomDao.deleteRoom(room);
                if(p1 == 0){
                    bpDao.insertBattleProcess(room,r.getAgainstA()+"获胜");
                }else{
                    bpDao.insertBattleProcess(room,r.getAgainstB()+"获胜");
                }
                js.put("type","over");
                for (String s : Controller.LINKS.keySet()) {
                    Controller.LINKS.get(s).sendMessage(js.toString());
                }
            }else if(js.getString("flag") != null){
                roomDao.deleteRoom(room);
                bpDao.insertBattleProcess(room,"平局");
                js.put("type","over");
                for (String s : Controller.LINKS.keySet()) {
                    Controller.LINKS.get(s).sendMessage(js.toString());
                }
            }
        }
        else {
            for (String s : Controller.LINKS.keySet()) {
                if (s.equals(room.getAgainstA()) || s.equals(room.getAgainstB())) {
                    Controller.LINKS.get(s).sendMessage(js.toString());
                }
            }
        }
    }
}
