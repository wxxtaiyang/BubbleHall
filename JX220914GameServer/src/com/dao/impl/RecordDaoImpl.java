package com.dao.impl;

import com.dao.RecordDao;
import com.util.DBUtils;
import model.Record;

import java.util.ArrayList;
import java.util.List;

public class RecordDaoImpl implements RecordDao {
    DBUtils<Record> dbUtils = new DBUtils<>();
    @Override
    public List<Record> selectAllRecord(String sendid,String recvid) {
        String sql = "select * from record where recvid=?";
        ArrayList<Object> parameter = new ArrayList<>();
        if(recvid.equals("all")){
            parameter.add(recvid);
        }else {
            sql= "select * from record where (sendid=? and recvid=?) or (sendid=? and recvid=?)";
            parameter.add(sendid);
            parameter.add(recvid);
            parameter.add(recvid);
            parameter.add(sendid);
        }
        return dbUtils.selectData(sql,parameter,Record.class);
    }

    @Override
    public boolean insertRecord(Record record) {
        String sql = "insert into record(sendid,recvid,rectype,content,rectime,state) " +
                "values(?,?,?,?,now(),?)";
        ArrayList<Object> params = new ArrayList<>();
        params.add(record.getSendId());
        params.add(record.getRecvId());
        params.add(record.getRecType());
        params.add(record.getContent());
        params.add(record.getState());

        return dbUtils.updateData(sql,params);
    }
}
