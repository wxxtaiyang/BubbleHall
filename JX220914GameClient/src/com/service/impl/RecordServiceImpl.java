package com.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.controller.Controller;
import com.net.ClientLink;
import com.service.BusinessService;
import com.util.Action;
import com.view.game.GHallTake;
import model.Record;

import java.util.List;

@Action("record")
public class RecordServiceImpl implements BusinessService {
    private Controller con;
    @Override
    public void doBusinessService(JSONObject js, ClientLink link) {
        List<Record> records = js.getJSONArray("data").toJavaList(Record.class);
        GHallTake.history.put(js.getString("recvid"),records);
        con.ghFrame.ghtake.setModels(GHallTake.recvId);
    }
}
