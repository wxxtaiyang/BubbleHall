package com.controller;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class AllWindowAct extends WindowAdapter {

    @Override
    public void windowClosing(WindowEvent e) {
        int ans = JOptionPane.showConfirmDialog(null,"确认退出？","退出"
                ,JOptionPane.YES_NO_OPTION);
        if(ans == 0) // 判断是否点击了是
            System.exit(0);

    }
}
