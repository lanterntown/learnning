package com.lanterntown.learnning.concurrent;

import sun.plugin.javascript.navig.LinkArray;

import java.util.ArrayList;
import java.util.List;

public class NonVisibilityDemo {
    public static void main(String[] args) throws InterruptedException {
        ReadThread readThread = new ReadThread();
        readThread.start();
        Thread.sleep(1000L);
        WriteThread writeThread = new WriteThread();
        writeThread.start();
    }
}


class ReadThread extends Thread {

    @Override
    public void run() {
        System.out.println("read-thread start");
        while (true) {
            List a = WriteThread.getList();
            if (a.size() == 1) {
                System.out.println("read-thread end");
                break;
            }
        }
    }
}

class WriteThread extends Thread {
    private static List<String> list = new ArrayList<>();

    public static List<String> getList() {
        return list;
    }

    @Override
    public void run() {
        list = new ArrayList<>();
        list.add("11111");
    }
}