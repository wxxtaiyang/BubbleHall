package com.view.game;

import com.commen.ImageData;
import com.controller.Controller;
import model.Role;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MapPanel extends JPanel {
    private Controller con;
    public static int mapEvel = 2;
    public static int[][] map;// 房间地图
    public static int[][][] maps = new int[4][15][15];// 地图集合
    public static boolean flag = true;

    static {
        Random rd = new Random();
        for (int i = 0; i < maps.length; i++) {
            for (int j = 0; j < maps[i].length; j++) {
                for (int k = 0; k < maps[i][j].length; k++) {
                    if (k % 2 == 0 && j % 2 == 0) {
                        maps[i][j][k] = rd.nextInt(2) - 2;
                    } else {
                        maps[i][j][k] = rd.nextInt(3);
                    }
                }
            }
            maps[i][6][6] = 0;
            maps[i][6][8] = 0;
            maps[i][6][7] = 0;/*map[i][8][11] = 0;*/
            maps[i][7][8] = 0;
            maps[i][7][7] = 0;
            maps[i][7][6] = 0;/*map[i][9][11] = 0;*/
            maps[i][8][8] = 0;
            maps[i][8][7] = 0;
            maps[i][8][6] = 0;/*map[i][10][11] = 0;*/
//            map[i][11][8] = 0;map[i][11][9] = 0;map[i][11][10] = 0;map[i][11][11] = 0;
            maps[i][0][0] = 0;
            maps[i][0][1] = 0;
            maps[i][1][0] = 0;
            maps[i][1][1] = 0;
            maps[i][14][14] = 0;
            maps[i][14][13] = 0;
            maps[i][13][14] = 0;
            maps[i][13][13] = 0;

        }

    }


    public MapPanel(Controller con) {
        this.con = con;
        map = new int[15][15];
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(ImageData.BGMPAS.get(mapEvel).getImage(), 0, 0, 605, 605, null);
        g.drawImage(new ImageIcon("resources\\map\\" + (mapEvel + 1) + "\\center.png").getImage(), 240, 240, 120, 120, null);
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                switch (map[i][j]) {
                    case -1:
                        g.drawImage(ImageData.BLOCKS.get(mapEvel + 1).get(0).getImage(), i * 40, j * 40, 40, 40, null);
                        break;
                    case -2:
                        g.drawImage(ImageData.BLOCKS.get(mapEvel + 1).get(1).getImage(), i * 40, j * 40, 40, 40, null);
                        break;
                    case 1:
                        g.drawImage(ImageData.VANISH.get(mapEvel + 1).get(0).getImage(), i * 40, j * 40, 40, 40, null);
                        break;
                    case 2:
                        g.drawImage(ImageData.VANISH.get(mapEvel + 1).get(1).getImage(), i * 40, j * 40, 40, 40, null);
                        break;
                    case 10:
                    case 20:
                        g.drawImage(ImageData.BOM.getImage(), i * 40, j * 40, 40, 40, null);
                        break;
                    case 21:
                        g.drawImage(ImageData.POWERS.getImage(), i * 40, j * 40, 40, 40, null);
                        break;
                    case 31:
                        g.drawImage(ImageData.ITEMBOMB.getImage(), i * 40, j * 40, 40, 40, null);
                        break;
                    case 32:
                        g.drawImage(ImageData.ITEMPOWER.getImage(), i * 40, j * 40, 40, 40, null);
                        break;
                    case 33:
                        g.drawImage(ImageData.ITEMBLOOD.getImage(), i * 40, j * 40, 40, 40, null);
                        break;
                    case 34:
                        g.drawImage(ImageData.ITEMSHOES.getImage(), i * 40, j * 40, 40, 40, null);
                        break;
                }
            }
        }

        if (con.wgFrame.wgPanel.getPlay1() != null) {
            Role p1 = con.wgFrame.wgPanel.getPlay1();
            g.drawImage(new ImageIcon(p1.getImage()).getImage(), p1.getPointX(), p1.getPointY() - 20, 50, 60, null);
            g.drawImage(new ImageIcon(p1.getHat()).getImage(), p1.getPointX() + 9, p1.getPointY() - 20, 35, 20, null);
            g.drawImage(new ImageIcon(p1.getHat()).getImage(), p1.getPointX() + 17, p1.getPointY() + 40 - 20, 17, 14, null);

        }
        if (con.wgFrame.wgPanel.getPlay2() != null) {
            Role p2 = con.wgFrame.wgPanel.getPlay2();
            g.drawImage(new ImageIcon(p2.getImage()).getImage(), p2.getPointX(), p2.getPointY() - 20, 50, 60, null);
            g.drawImage(new ImageIcon(p2.getHat()).getImage(), p2.getPointX() + 9, p2.getPointY() - 20, 35, 20, null);
            g.drawImage(new ImageIcon(p2.getHat()).getImage(), p2.getPointX() + 17, p2.getPointY() + 40 - 20, 17, 14, null);

        }
    }


}
