package com.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.controller.Controller;
import com.net.ClientHeart;
import com.net.ClientLink;
import com.service.BusinessService;
import com.util.Action;

@Action("heart")
public class HeartServiceImpl implements BusinessService {
    private Controller con;

    @Override
    public void doBusinessService(JSONObject js, ClientLink link) {
        ClientHeart.NUM = 0;
    }
}
