package com.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.controller.Controller;
import com.dao.RecordDao;
import com.dao.impl.RecordDaoImpl;
import com.net.SocketLink;
import com.service.BusinessService;
import com.util.Action;
import model.Record;

@Action("send")
public class SendServiceImpl implements BusinessService {
    private RecordDao recordDao = new RecordDaoImpl();

    @Override
    public void doBusinessService(JSONObject js, SocketLink link) {
        String sendid = js.getString("sendid");
        String recvid = js.getString("recvid");
        String content = js.getString("content");
        int type = js.getInteger("type");
        Record record = new Record();
        record.setSendId(sendid);
        record.setRecvId(recvid);
        record.setContent(content);
        record.setRecType(type);
        record.setState("已读");
        boolean flag = recordDao.insertRecord(record);

        if(type == 0){ // 转发判断
            for (SocketLink value : Controller.LINKS.values()) {
                value.sendMessage(js.toString());
            }
        }else{
            for (String s : Controller.LINKS.keySet()) {
                if(s.equals(recvid)){
                    Controller.LINKS.get(s).sendMessage(js.toString());
                    break;
                }
            }
        }
    }
}
