package org.example.suanfa.thread;

public class StrictAlternatePrinter {
    private static final Object lock = new Object();
    private static boolean printNumber = true; // 控制交替打印的标志
    private static boolean numbersDone = false; // 数字是否打印完
    private static boolean lettersDone = false; // 字母是否打印完

    public static void main(String[] args) {
        // 打印数字的线程（1-10）
        Thread numberThread = new Thread(() -> {
            synchronized (lock) {
                for (int i = 1; i <= 10; i++) {
                    while (!printNumber && !lettersDone) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    if (lettersDone) break;

                    System.out.print(i + " ");
                    printNumber = false;
                    lock.notifyAll();
                }
                numbersDone = true;
                lock.notifyAll(); // 通知字母线程数字已完成
            }
        });

        // 打印字母的线程（A-Z）
        Thread letterThread = new Thread(() -> {
            synchronized (lock) {
                for (char c = 'A'; c <= 'Z'; c++) {
                    while (printNumber && !numbersDone) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    if (numbersDone) {
                        // 如果数字已完成，继续打印剩余字母
                        System.out.print(c + " ");
                        continue;
                    }

                    System.out.print(c + " ");
                    printNumber = true;

                    if (c == 'Z') {
                        lettersDone = true;
                    }
                    lock.notifyAll();
                }
                lettersDone = true;
                lock.notifyAll(); // 通知数字线程字母已完成
            }
        });

        numberThread.start();
        letterThread.start();

        // 等待两个线程结束
        try {
            numberThread.join();
            letterThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}