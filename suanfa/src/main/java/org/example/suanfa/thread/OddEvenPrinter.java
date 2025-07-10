package org.example.suanfa.thread;

public class OddEvenPrinter {
    private static int count = 0;          // 共享计数器
    private static final Object lock = new Object();  // 同步锁对象

    public static void main(String[] args) {
        // 偶数打印线程
        new Thread(() -> {
            while (count < 100) {
                synchronized (lock) {
                    if ((count & 1) == 0) {  // 判断是否为偶数
                        System.out.println("偶数: " + count++);
                    }
                }
            }
        }).start();

        // 奇数打印线程
        new Thread(() -> {
            while (count < 100) {
                synchronized (lock) {
                    if ((count & 1) == 1) {  // 判断是否为奇数
                        System.out.println("奇数: " + count++);
                    }
                }
            }
        }).start();
    }
}