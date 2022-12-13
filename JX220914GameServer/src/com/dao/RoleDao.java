package com.dao;

import model.Role;

public interface RoleDao {
    /**
     * 根据id查询角色信息
     * @param userId 用户id
     * @return role
     */
    Role selectRoleByUserId(String userId);

    /**
     * 根据id修改角色图片信息
     * @param role 角色id
     * @return boolean
     */
    boolean updateRoleByUserId(Role role);

}
