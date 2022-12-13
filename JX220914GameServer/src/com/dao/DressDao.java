package com.dao;

import model.Dress;

import java.util.List;

public interface DressDao {
    /**
     * 查询所有的服饰信息
     * @return list
     */
    List<Dress> selectAllDress();
}
