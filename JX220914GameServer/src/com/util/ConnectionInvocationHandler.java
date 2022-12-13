package com.util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.LinkedList;

public class ConnectionInvocationHandler implements InvocationHandler {
    private Connection ctn;
    private LinkedList<Connection> ctns;
    private int maxLink;


    public ConnectionInvocationHandler(Connection ctn, LinkedList<Connection> ctns, int maxLink) {
        this.ctn = ctn;
        this.ctns = ctns;
        this.maxLink = maxLink;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(ctn == null){
            throw new Exception("连接已经关闭，无法使用！请重新获取连接");
        }
        if(method.getName().equals("close")){
            if(ctns.size() < maxLink) {
                ctns.add(ctn);
            }
            ctn = null;
            return null;
        }else{
            return method.invoke(ctn,args);
        }
    }
}
