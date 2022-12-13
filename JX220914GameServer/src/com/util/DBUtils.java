package com.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;

public class DBUtils<T> {
    private SunlitPool sunPool;

    public DBUtils() {
        this.sunPool = SunlitPool.getInstance();
    }

    /**
     * 读数据库中数据方法（查询）
     * @param sql 查询语句
     * @param parameter 参数数组，与语句中通配符一一对应，无通配符传空即可
     * @param cla 返回类型
     * @return ArrayList
     */
    public ArrayList<T> selectData(String sql, ArrayList<Object> parameter, Class<T> cla){
        ArrayList<T> resultData = new ArrayList<>();
        Connection ctn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            ctn = sunPool.getConnection();
            ps = ctn.prepareStatement(sql);
            if(parameter != null) {
                for (int i = 0; i < parameter.size(); i++) {
                    ps.setObject(i+1, parameter.get(i));
                }
            }
            rs = ps.executeQuery();
            Field[] fs = cla.getDeclaredFields();// 获取所有参数列表
//            String[] msName = new String[fs.length];// 获取所有参数列表对应的set方法名
//            for (int i = 0; i < msName.length; i++) {
//                String pName = fs[i].getName();
//                String names = pName.substring(0,1);
//                names = names.toUpperCase();
//                msName[i] = "set"+names+fs[i].getName().substring(1);
//            }
            while(rs.next()){
                // 反射
                Class clazz = Class.forName(cla.getName());
                T t = (T) clazz.newInstance();
                for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                    // 去掉数据库里面的下划线和类中的下划线
                    String name = rs.getMetaData().getColumnName(i+1);
                    name = name.replace("_","");

                    for (int j = 0; j < fs.length; j++) {
                        if(fs[j].getName().equalsIgnoreCase(name)) { // 判断当前sql得到的列名等于属性名
//                            Method method = cla.getMethod(msName[j], fs[j].getType());
                            if(rs.getObject(i + 1) == null) {
                                continue;
                            }else if(rs.getObject(i + 1).getClass() == Date.class
                                    || rs.getObject(i + 1).getClass() == Timestamp.class) {
                                fs[j].setAccessible(true); // 通过属性赋值
                                fs[j].set(t,rs.getString(i+1));
//                                method.setAccessible(true);// 通过方法赋值
//                                method.invoke(t, rs.getString(i + 1));
                            }else{
                                fs[j].setAccessible(true); // 通过属性赋值
                                fs[j].set(t,rs.getObject(i+1));
//                                method.setAccessible(true); // 通过方法赋值
//                                method.invoke(t, rs.getObject(i + 1));
                            }
                            break;
                        }
                    }
                }
                resultData.add(t);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            sunPool.closeAll(ctn,ps,rs);
        }
        return resultData;
    }

    /**
     * 写数据库中数据的方法，包扣修改，删除，添加
     * @param sql 写数据的sql语句
     * @param parameter 参数列表，与语句的通配符一一对应。
     * @return boolean
     */
    public boolean updateData(String sql,ArrayList<Object> parameter){
        boolean flag = false;
        Connection ctn = null;
        PreparedStatement ps = null;
        try{
            ctn = sunPool.getConnection();
            ps = ctn.prepareStatement(sql);
            for (int i = 0; i < parameter.size(); i++) {
                ps.setObject(i+1,parameter.get(i));
            }
            int ans = ps.executeUpdate();
            if(ans > 0){
                flag = true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            sunPool.closeAll(ctn,ps,null);
        }
        return flag;
    }
}
