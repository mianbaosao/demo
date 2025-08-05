package org.example.suanfa.thread;

import java.util.Scanner;

/**
 * @author heweitao538 2025/7/17
 */
public class ThreadTestCntN {
    public static final Object lock = new Object();
    public static int currentNumber = 1;  // 当前要打印的数字
    public static int maxNumber = 100;   // 最大打印数字
    public static int currentThread = 0;  // 当前要打印数字的线程ID

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();  // 输入线程数量
        for (int i = 0; i < n; i++) {
            final int threadId = i;
            new Thread(() -> {
                printNumbers(threadId, n);
            }).start();
        }
    }

    private static void printNumbers(int threadId, int totalThreads) {
        while (true) {
            synchronized (lock) {
                if (currentNumber > maxNumber) {
                    break;
                }
                if (currentThread == threadId) {
                    int vo=threadId+1;
                    System.out.println("Thread-" + vo + ": " + currentNumber);
                    currentNumber++;
                    currentThread = (currentThread + 1) % totalThreads;
                    lock.notifyAll();
                } else {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }
    }
}