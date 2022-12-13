package com.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.controller.Controller;
import com.net.ClientLink;
import com.service.BusinessService;
import com.util.Action;
import com.view.game.GHallTake;
import model.Record;

import java.text.SimpleDateFormat;
import java.util.Date;

@Action("send")
public class SendServiceImpl implements BusinessService {
    private Controller con;

    @Override
    public void doBusinessService(JSONObject js, ClientLink link) {
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
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        String time = format.format(new Date());
        record.setRecTime(time);
        if (type == 0) {
            GHallTake.history.get(recvid).add(record);
            if(GHallTake.recvId.equals(recvid))
                con.ghFrame.ghtake.setModels(recvid);
        }else{
            GHallTake.history.get(sendid).add(record);
            if(GHallTake.recvId.equals(sendid))
                con.ghFrame.ghtake.setModels(sendid);
        }
    }
}
