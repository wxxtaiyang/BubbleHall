package com.commen;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 图片资源界面接口
 */
public class ImageData {
    public static List<ImageIcon> HATS = new ArrayList<>(); // 帽子图片
    public static List<ImageIcon> CLOTHES = new ArrayList<>();// 衣服图片
    public static List<ImageIcon> BODYS = new ArrayList<>();// 角色图片
    public static List<ImageIcon> HEADS = new ArrayList<>();// 头像图片
    public static List<ImageIcon> BGMPAS = new ArrayList<>();// 地图背景图片
    public static Map<Integer,List<ImageIcon>> BLOCKS = new HashMap<>();// 地图障碍物
    public static Map<Integer,List<ImageIcon>> VANISH = new HashMap<>();// 地图可消方块

    // 背景图片
    public static ImageIcon LOGIN = new ImageIcon("resources\\image\\login.png");
    public static ImageIcon REGISTER = new ImageIcon("resources\\image\\register.jpeg");
    public static ImageIcon BGLIGHT = new ImageIcon("resources\\image\\bglight.jpeg");
    public static ImageIcon BGNIGHT = new ImageIcon("resources\\image\\bgnight.jpeg");
    public static ImageIcon TAKE = new ImageIcon("resources\\image\\take.jpeg");
    public static ImageIcon PERSONAL = new ImageIcon("resources\\image\\personal.png");
    public static ImageIcon PERPENAL = new ImageIcon("resources\\image\\perPanel.jpeg");
    public static ImageIcon GAMEPLAYER = new ImageIcon("resources\\image\\gameP.png");
    public static ImageIcon WAITBG = new ImageIcon("resources\\image\\wait.png");
    // 房间按钮标签
    public static ImageIcon ROOMICON = new ImageIcon("resources\\image\\roomIcon.jpeg");
    public static ImageIcon ROOMBG = new ImageIcon("resources\\image\\roomBg.png");
    public static ImageIcon ROOMBG1 = new ImageIcon("resources\\image\\roomBg1.jpeg");
    // 音乐按钮图标
    public static ImageIcon YINYUE = new ImageIcon("resources\\music\\yinyue.png");
    public static ImageIcon YINXIAO = new ImageIcon("resources\\music\\yinxiao.png");
    public static ImageIcon CLOSEYINYUE = new ImageIcon("resources\\music\\closeyinyue.png");
    public static ImageIcon CLOSEYINXIAO = new ImageIcon("resources\\music\\closeyinxiao.png");
    // 翻页图标
    public static ImageIcon RIGHT = new ImageIcon("resources\\image\\btn\\right.png");
    public static ImageIcon LEFT = new ImageIcon("resources\\image\\btn\\left.png");
    // 头像图标
    public static ImageIcon P1 = new ImageIcon("resources\\image\\heart\\P1.png");
    public static ImageIcon P2 = new ImageIcon("resources\\image\\heart\\P2.png");
    public static ImageIcon P3 = new ImageIcon("resources\\image\\heart\\P3.png");
    public static ImageIcon P4 = new ImageIcon("resources\\image\\heart\\P4.png");
    public static ImageIcon P5 = new ImageIcon("resources\\image\\heart\\P5.png");
    public static ImageIcon P6 = new ImageIcon("resources\\image\\heart\\P6.png");
    public static ImageIcon P7 = new ImageIcon("resources\\image\\heart\\P7.png");
    public static ImageIcon P8 = new ImageIcon("resources\\image\\heart\\P8.png");
    // 地图显示图片
    public static ImageIcon map1 = new ImageIcon("resources\\map\\1.png");
    public static ImageIcon map2 = new ImageIcon("resources\\map\\2.png");
    public static ImageIcon map3 = new ImageIcon("resources\\map\\3.png");
    public static ImageIcon map4 = new ImageIcon("resources\\map\\4.png");
    // 道具图标
    public static ImageIcon ITEMBOMB = new ImageIcon("resources\\image\\items\\zadan.png");
    public static ImageIcon ITEMBLOOD = new ImageIcon("resources\\image\\items\\xin.png");
    public static ImageIcon ITEMSHOES = new ImageIcon("resources\\image\\items\\xiezi.png");
    public static ImageIcon ITEMPOWER = new ImageIcon("resources\\image\\items\\power.png");
    // 其他图标
    public static ImageIcon READY = new ImageIcon("resources\\image\\ready.png");
    //游戏界面左侧图标
    public static ImageIcon BOMB = new ImageIcon("resources\\image\\information\\zadan.png");
    public static ImageIcon POWER = new ImageIcon("resources\\image\\information\\power.png");
    public static ImageIcon SHORT = new ImageIcon("resources\\image\\information\\xiezi.png");
    public static ImageIcon BOOLD = new ImageIcon("resources\\image\\information\\xin.png");

    public static ImageIcon[] TIMEDOWN = {
            new ImageIcon("resources\\image\\5.png"),
            new ImageIcon("resources\\image\\4.png"),
            new ImageIcon("resources\\image\\3.png"),
            new ImageIcon("resources\\image\\2.png"),
            new ImageIcon("resources\\image\\1.png")
    };
    public static ImageIcon BOM = new ImageIcon("resources\\expression\\55.gif");
    public static ImageIcon POWERS = new ImageIcon("resources\\image\\bomb.png");

    static {
        // 头像图标
        HEADS.add(new ImageIcon("resources\\image\\heart\\P1.png"));
        HEADS.add(new ImageIcon("resources\\image\\heart\\P2.png"));
        HEADS.add(new ImageIcon("resources\\image\\heart\\P3.png"));
        HEADS.add(new ImageIcon("resources\\image\\heart\\P4.png"));
        HEADS.add(new ImageIcon("resources\\image\\heart\\P5.png"));
        HEADS.add(new ImageIcon("resources\\image\\heart\\P6.png"));
        HEADS.add(new ImageIcon("resources\\image\\heart\\P7.png"));
        HEADS.add(new ImageIcon("resources\\image\\heart\\P8.png"));
        // 人物图像
        BODYS.add(new ImageIcon("resources\\image\\role\\P1.png"));
        BODYS.add(new ImageIcon("resources\\image\\role\\P2.png"));
        BODYS.add(new ImageIcon("resources\\image\\role\\P3.png"));
        BODYS.add(new ImageIcon("resources\\image\\role\\P4.png"));
        BODYS.add(new ImageIcon("resources\\image\\role\\P5.png"));
        BODYS.add(new ImageIcon("resources\\image\\role\\P6.png"));
        BODYS.add(new ImageIcon("resources\\image\\role\\P7.png"));
        BODYS.add(new ImageIcon("resources\\image\\role\\P8.png"));
        // 帽子图片
        HATS.add(new ImageIcon("resources\\heads\\head01.png"));
        HATS.add(new ImageIcon("resources\\heads\\head02.png"));
        HATS.add(new ImageIcon("resources\\heads\\head03.png"));
        HATS.add(new ImageIcon("resources\\heads\\head04.png"));
        // 衣服图片
        CLOTHES.add(new ImageIcon("resources\\clothes\\clothes01.png"));
        CLOTHES.add(new ImageIcon("resources\\clothes\\clothes02.png"));
        CLOTHES.add(new ImageIcon("resources\\clothes\\clothes03.png"));
        CLOTHES.add(new ImageIcon("resources\\clothes\\clothes04.png"));
        // 地图背景
        BGMPAS.add(new ImageIcon("resources\\map\\1\\bg.png"));
        BGMPAS.add(new ImageIcon("resources\\map\\2\\bg.png"));
        BGMPAS.add(new ImageIcon("resources\\map\\3\\bg.png"));
        BGMPAS.add(new ImageIcon("resources\\map\\4\\bg.png"));
        // 地图障碍物
        List<ImageIcon> list01 = new ArrayList<>();
        list01.add(new ImageIcon("resources\\map\\1\\rub1.png"));
        list01.add(new ImageIcon("resources\\map\\1\\rub2.png"));
        List<ImageIcon> list02 = new ArrayList<>();
        list02.add(new ImageIcon("resources\\map\\2\\rub1.png"));
        list02.add(new ImageIcon("resources\\map\\2\\rub2.png"));
        List<ImageIcon> list03 = new ArrayList<>();
        list03.add(new ImageIcon("resources\\map\\3\\rub1.png"));
        list03.add(new ImageIcon("resources\\map\\3\\rub2.png"));
        List<ImageIcon> list04 = new ArrayList<>();
        list04.add(new ImageIcon("resources\\map\\4\\rub1.png"));
        list04.add(new ImageIcon("resources\\map\\4\\rub2.png"));
        BLOCKS.put(1,list01);BLOCKS.put(2,list02);
        BLOCKS.put(3,list03);BLOCKS.put(4,list04);
        // 地图可消方块
        List<ImageIcon> l01 = new ArrayList<>();
        l01.add(new ImageIcon("resources\\map\\1\\close1.png"));
        l01.add(new ImageIcon("resources\\map\\1\\close2.png"));
        List<ImageIcon> l02 = new ArrayList<>();
        l02.add(new ImageIcon("resources\\map\\2\\close1.png"));
        l02.add(new ImageIcon("resources\\map\\2\\close2.png"));
        List<ImageIcon> l03 = new ArrayList<>();
        l03.add(new ImageIcon("resources\\map\\3\\close1.png"));
        l03.add(new ImageIcon("resources\\map\\3\\close2.png"));
        List<ImageIcon> l04 = new ArrayList<>();
        l04.add(new ImageIcon("resources\\map\\4\\close1.png"));
        l04.add(new ImageIcon("resources\\map\\4\\close2.png"));
        VANISH.put(1,l01);VANISH.put(2,l02);
        VANISH.put(3,l03);VANISH.put(4,l04);


    }

}
