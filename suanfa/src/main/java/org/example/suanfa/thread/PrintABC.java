package org.example.suanfa.thread;

/**
 * abc线程循环打印abc诗词
 */
public class PrintABC {
    private static final Object lock = new Object(); // 锁对象
    private static int state = 0; // 状态变量，用于控制打印顺序

    public static void main(String[] args) {
        Thread threadA = new Thread(() -> print("A", 0));
        Thread threadB = new Thread(() -> print("B", 1));
        Thread threadC = new Thread(() -> print("C", 2));

        threadA.start();
        threadB.start();
        threadC.start();
    }

    private static void print(String name, int targetState) {
        for (int i = 0; i < 10; i++) {
            synchronized (lock) {
                while (state % 3 != targetState) { // 检查是否轮到自己打印
                    try {
                        lock.wait(); // 如果不是，则等待
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.print(name); // 打印
                state++; // 更新状态
                lock.notifyAll(); // 唤醒其他线程
            }
        }
    }
}