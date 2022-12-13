package com.dao;

import model.Record;

import java.util.List;

public interface RecordDao {
    /**
     * 通过发送者和接收者id得到所有信息
     * @param sendid 发送者
     * @param recvid 接收者
     * @return list
     */
    List<Record> selectAllRecord(String sendid,String recvid);

    /**
     * 添加历史记录
     * @param record 记录类别
     * @return boolean
     */
    boolean insertRecord(Record record);
}
