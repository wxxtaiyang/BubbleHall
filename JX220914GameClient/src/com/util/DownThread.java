package com.util;

import com.controller.Controller;

public class DownThread extends Thread {
    private Controller con;
    public DownThread(Controller con) {
        this.con = con;
    }

    @Override
    public void run() {
        int i = 0;
        while (con.wgFrame.wgPanel.isP1Ready() && con.wgFrame.wgPanel.isP2Ready() && i < 5) {
            con.wgFrame.wgPanel.setCountDown(i);
            con.wgFrame.repaint();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i++;
        }
        if(con.wgFrame.wgPanel.isP1Ready() && con.wgFrame.wgPanel.isP2Ready() && i >= 5) {
            con.wgFrame.wgPanel.getPlay1().setPointX(0);
            con.wgFrame.wgPanel.getPlay1().setPointY(0);
            con.wgFrame.wgPanel.getPlay1().setNormal();

            con.wgFrame.wgPanel.getPlay2().setPointX(560);
            con.wgFrame.wgPanel.getPlay2().setPointY(560);
            con.wgFrame.wgPanel.getPlay2().setNormal();

            Thread t = new Thread(con.gameKeyAct);
            t.start();

            CheckThread checkThread = new CheckThread(con);
            checkThread.start();

            con.mFrame.mpPanel.getP11().setText(": "+con.wgFrame.wgPanel.getPlay1().getBomNum());
            con.mFrame.mpPanel.getP12().setText(": "+con.wgFrame.wgPanel.getPlay1().getSpeed());
            con.mFrame.mpPanel.getP13().setText(": "+con.wgFrame.wgPanel.getPlay1().getPower());
            con.mFrame.mpPanel.getP14().setText(": "+con.wgFrame.wgPanel.getPlay1().getBlood());

            con.mFrame.mpPanel.getP21().setText(": "+con.wgFrame.wgPanel.getPlay2().getBomNum());
            con.mFrame.mpPanel.getP22().setText(": "+con.wgFrame.wgPanel.getPlay2().getSpeed());
            con.mFrame.mpPanel.getP23().setText(": "+con.wgFrame.wgPanel.getPlay2().getPower());
            con.mFrame.mpPanel.getP24().setText(": "+con.wgFrame.wgPanel.getPlay2().getBlood());

            con.wgFrame.setVisible(false);
            con.mFrame.setVisible(true);
            System.out.println("倒计时线程结束");
        }
    }
}
