package com.service;

import com.alibaba.fastjson.JSONObject;
import com.net.SocketLink;

public interface BusinessService {
    /**
     * 读取客户端发送的数据并做响应
     * @param js 接收的数据（JSON）
     * @param link 得到该客户端的socket线程
     */
    void doBusinessService(JSONObject js, SocketLink link);
}
