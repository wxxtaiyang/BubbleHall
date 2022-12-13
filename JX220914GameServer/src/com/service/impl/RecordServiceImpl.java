package com.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.controller.Controller;
import com.dao.RecordDao;
import com.dao.impl.RecordDaoImpl;
import com.net.SocketLink;
import com.service.BusinessService;
import com.util.Action;
import model.Record;

import java.util.List;

@Action("record")
public class RecordServiceImpl implements BusinessService {
    private RecordDao recordDao = new RecordDaoImpl();
    @Override
    public void doBusinessService(JSONObject js, SocketLink link) {
        String sendid = js.getString("sendid");
        String recvid = js.getString("recvid");
        List<Record> records = recordDao.selectAllRecord(sendid,recvid);
        JSONObject jsp = new JSONObject();
        jsp.put("action","record");
        jsp.put("recvid",recvid);
        jsp.put("data",records);
        link.sendMessage(jsp.toString());
    }
}
