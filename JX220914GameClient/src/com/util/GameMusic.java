package com.util;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;


public class GameMusic {
    //wav文件的路径
    private File file;
    //是否循环播放
    private volatile boolean isLoop = false;
    //是否正在播放
    private volatile boolean isPlay = false;
    //调节音量
    private float newVolumn = 7;

    private PlayThread playThread;

    public GameMusic(String srcPath) {
        file = new File(srcPath);
    }
    //播放音乐
    public void play() {
        if (playThread == null) {
            playThread = new PlayThread();
            playThread.start();
        }
    }

    //结束音乐
    public void over() {
        isPlay = false;
        if (playThread != null) {
            playThread = null;
        }
    }

   //设置循环播放
    public GameMusic setLoop(boolean isLoop) {
        this.isLoop = isLoop;
        return this;
    }

//     测试,越小音量越小
    public GameMusic setVolumn(float newVolumn) {
        this.newVolumn = newVolumn;
        return this;
    }
    //异步线程
    private class PlayThread extends Thread {
        @Override
        public void run() {
            isPlay = true;
            do {
                SourceDataLine sourceDataLine = null;
                BufferedInputStream bufIn = null;
                AudioInputStream audioIn = null;
                try {
                    bufIn = new BufferedInputStream(new FileInputStream(file));
                    audioIn = AudioSystem.getAudioInputStream(bufIn); // 可直接传入file

                    AudioFormat format = audioIn.getFormat();
                    sourceDataLine = AudioSystem.getSourceDataLine(format);
                    sourceDataLine.open();
                    // 必须open之后
                    if (newVolumn != 7) {
                        FloatControl control = (FloatControl) sourceDataLine.getControl(FloatControl.Type.MASTER_GAIN);
                        control.setValue(newVolumn);
                    }

                    sourceDataLine.start();
                    byte[] buf = new byte[512];
//					System.out.println(audioIn.available());
                    int len = -1;
                    while (isPlay && (len = audioIn.read(buf)) != -1) {
                        sourceDataLine.write(buf, 0, len);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {

                    if (sourceDataLine != null) {
                        sourceDataLine.drain();
                        sourceDataLine.close();
                    }
                    try {
                        if (bufIn != null) {
                            bufIn.close();
                        }
                        if (audioIn != null) {
                            audioIn.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } while (isPlay && isLoop);
        }
    }

}
