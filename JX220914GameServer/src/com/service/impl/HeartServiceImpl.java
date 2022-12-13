package com.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.net.SocketLink;
import com.service.BusinessService;
import com.util.Action;

@Action("heart")
public class HeartServiceImpl implements BusinessService {
    @Override
    public void doBusinessService(JSONObject js, SocketLink link) {
        link.sendMessage(js.toString());
    }
}
