package com.commen;

import com.util.GameMusic;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

/**
 * 音乐资源文件接口
 */
public class MusicData {
    //创建相当于音乐播放器的对象
//    public static GameMusic waitGame;// 等待开始
    public static GameMusic click;// 放置炸弹
    public static GameMusic bom;// 炸弹爆炸
    public static GameMusic bgMusic01;// 背景音乐1
    public static GameMusic bgMusic02;// 背景音乐2

    static {
        try {
//            waitGame = new GameMusic("resources\\music\\waitToGame.mp3");

            click = new GameMusic("resources\\music\\click.wav");

            bom = new GameMusic("resources\\music\\bom.wav");

            bgMusic01 = new GameMusic("resources\\music\\bgMusic01.wav");
            bgMusic01.setLoop(true);

            bgMusic02 = new GameMusic("resources\\music\\bgMusic02.wav");
            bgMusic02.setLoop(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
