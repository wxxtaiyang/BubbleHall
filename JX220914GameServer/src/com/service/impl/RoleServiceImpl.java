package com.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.dao.RoleDao;
import com.dao.impl.RoleDaoImpl;
import com.net.SocketLink;
import com.service.BusinessService;
import com.util.Action;
import model.Role;

@Action("role")
public class RoleServiceImpl implements BusinessService {
    private RoleDao roleDao = new RoleDaoImpl();
    @Override
    public void doBusinessService(JSONObject js, SocketLink link) {
        Role role = js.getObject("role", Role.class);
        roleDao.updateRoleByUserId(role);
    }
}
