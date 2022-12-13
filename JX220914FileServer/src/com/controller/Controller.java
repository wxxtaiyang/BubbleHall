package com.controller;

import com.net.FileAccept;

public class Controller {
    public Controller() {

        FileAccept accept = new FileAccept(); // 创建文件服务端对象
        accept.start();
    }
}
