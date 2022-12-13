package com.service;

import com.alibaba.fastjson.JSONObject;
import com.net.ClientLink;

public interface BusinessService {
    /**
     * 读取服务器返回数据
     * @param js 数据类型
     * @param link 连接到服务器的线程
     */
    void doBusinessService(JSONObject js, ClientLink link);
}
