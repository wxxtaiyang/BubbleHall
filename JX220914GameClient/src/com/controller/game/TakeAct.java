package com.controller.game;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.support.hsf.HSFJSONUtils;
import com.controller.Controller;
import com.view.game.GHallTake;
import model.Record;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TakeAct implements ActionListener {
    private Controller con;

    public TakeAct(Controller con) {
        this.con = con;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("expression")){
            con.ghFrame.ghtake.gHallImage.setVisible(true);
        }else if(e.getActionCommand().equals("send")){
            JSONObject js = new JSONObject();
            js.put("action","send");
            js.put("sendid",con.ghFrame.getUser().getUsername());
            js.put("recvid",GHallTake.recvId);
            js.put("content",con.ghFrame.ghtake.getMsg().getText());
            int type = 1;// 私聊
            if(GHallTake.recvId.equals("all")) type = 0;// 群聊
            System.out.println(type);
            js.put("type",type);
            con.link.sendMessage(js.toString());
            Record record = new Record();
            record.setSendId(con.ghFrame.getUser().getUsername());
            record.setRecvId(GHallTake.recvId);
            record.setContent(con.ghFrame.ghtake.getMsg().getText());
            record.setRecType(type);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
            String time = format.format(new Date());
            record.setRecTime(time);
            if(type != 0) {
                GHallTake.history.get(GHallTake.recvId).add(record);
                con.ghFrame.ghtake.setModels(GHallTake.recvId);
            }
            con.ghFrame.ghtake.getMsg().setText("");
        }else if(e.getActionCommand().equals("all")){
            GHallTake.recvId = "all";
            con.ghFrame.ghtake.setModels("all");
        }
    }
}
