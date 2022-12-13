package com.dao.impl;

import com.dao.BattleProcessDao;
import com.util.DBUtils;
import model.BattleProcess;
import model.Room;

import java.util.ArrayList;
import java.util.List;


public class BattleProcessDaoImpl implements BattleProcessDao {
    DBUtils<BattleProcess> dbUtils = new DBUtils<>();

    @Override
    public boolean insertBattleProcess(Room room, String state) {
        String sql = "insert into battle_process(uaid,ubid,win,timeend) values(?,?,?,now())";
        ArrayList<Object> params = new ArrayList<>();
        params.add(room.getAgainstA());
        params.add(room.getAgainstB());
        params.add(state);
        return dbUtils.updateData(sql,params);
    }

    @Override
    public List<BattleProcess> queryBattleProcessByUser(String userid) {
        String sql = "select * from battle_process where uaid=? or ubid=?";
        ArrayList<Object> params = new ArrayList<>();
        params.add(userid);
        params.add(userid);
        return dbUtils.selectData(sql,params,BattleProcess.class);
    }
}
