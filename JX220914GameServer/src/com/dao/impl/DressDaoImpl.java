package com.dao.impl;

import com.dao.DressDao;
import com.util.DBUtils;
import model.Dress;

import java.util.List;

public class DressDaoImpl implements DressDao {
    private DBUtils<Dress> dbUtils = new DBUtils<>();
    @Override
    public List<Dress> selectAllDress() {
        String sql = "select * from dress";
        return dbUtils.selectData(sql,null,Dress.class);
    }
}
