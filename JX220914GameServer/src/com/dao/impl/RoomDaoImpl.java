package com.dao.impl;

import com.dao.RoomDao;
import com.util.DBUtils;
import model.Room;

import java.util.ArrayList;
import java.util.List;

public class RoomDaoImpl implements RoomDao {
    DBUtils<Room> dbUtils = new DBUtils<>();
    @Override
    public boolean insertRoom(Room room) {
        ArrayList<Object> params = new ArrayList<>();
        params.add(room.getMid());
        params.add(room.getAgainstA());
        params.add(room.getWinner());
        String sql = "insert into room(mid,againstA,winner) values(?,?,?)";
        return dbUtils.updateData(sql,params);
    }

    @Override
    public List<Room> selectAll() {
        String sql = "select * from room where winner != '取消'";
        return dbUtils.selectData(sql,null,Room.class);
    }

    @Override
    public boolean joinRoom(Room room) {
        String sql = "update room set againstB=?,winner=? where mid=?";
        ArrayList<Object> params = new ArrayList<>();
        params.add(room.getAgainstB());
        params.add(room.getWinner());
        params.add(room.getMid());
        return dbUtils.updateData(sql,params);
    }

    @Override
    public boolean deleteRoom(Room room) {
        String sql = "delete from room where mid=?";
        ArrayList<Object> params = new ArrayList<>();
        params.add(room.getMid());
        return dbUtils.updateData(sql,params);
    }
}
