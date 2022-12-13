package com.dao.impl;

import com.dao.RoleDao;
import com.util.DBUtils;
import model.Role;

import java.util.ArrayList;
import java.util.List;

public class RoleDaoImpl implements RoleDao {
    private DBUtils<Role> dbUtils = new DBUtils<>();

    @Override
    public Role selectRoleByUserId(String userId) {
        ArrayList<Object> params = new ArrayList<>();
        params.add(userId);
        String sql = "select * from role where name = ?";
        Role role = null;
        List<Role> roles = dbUtils.selectData(sql, params, Role.class);
        if(roles.size() != 0)
            role = roles.get(0);
        return role;
    }

    @Override
    public boolean updateRoleByUserId(Role role) {
        ArrayList<Object> params = new ArrayList<>();
        params.add(role.getName());
        String sql = "select * from role where name = ?";
        if(dbUtils.selectData(sql, params, Role.class).size() == 0) {
            sql = "insert into role(name,image,hat,clothes) value(?,?,?,?)";
            params.add(role.getImage());
            params.add(role.getHat());
            params.add(role.getClothes());
            return dbUtils.updateData(sql,params);
        } else {
            sql = "update role set image=?,hat=?,clothes=? where name=?";
            params.add(0, role.getClothes());
            params.add(0, role.getHat());
            params.add(0, role.getImage());
            return dbUtils.updateData(sql,params);
        }
    }
}
