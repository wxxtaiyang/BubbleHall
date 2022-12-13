package com.controller;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AllWindowAct extends WindowAdapter {

    @Override
    public void windowClosing(WindowEvent e) {
        int ans = JOptionPane.showConfirmDialog(null,"确认关闭服务器？","退出"
                ,JOptionPane.YES_NO_OPTION);
        if(ans == 0) // 判断是否点击了是
            System.exit(0);

    }
}
