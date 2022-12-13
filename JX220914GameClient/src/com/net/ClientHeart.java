package com.net;

import com.alibaba.fastjson.JSONObject;
import com.controller.Controller;

public class ClientHeart extends Thread {
    private ClientLink link;
    public static int NUM = 0;
    private Controller con;

    public ClientHeart(ClientLink link, Controller con) {
        this.link = link;
        this.con = con;
    }

    @Override
    public void run() {
        while (true) {
            JSONObject js = new JSONObject();
            js.put("action", "heart");
            link.sendMessage(js.toString());
            NUM++;
            if (NUM > 5) {// 如果未响应次数超过五次，关闭其他线程，打开新线程
                con.link.sendMessage(js.toString());
                con.connect = new ClientConnect(con);
                con.connect.start();
                return;
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
