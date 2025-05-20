package org.example.suanfa.thread;

public class abc {
    private static Object lock = new Object();
    private static Integer state = 1;
    public static void main(String[] args) {
        Thread ThreadA =  new Thread(() -> print("A",1));
        Thread ThreadB =  new Thread(() -> print("B",2));
        Thread ThreadC =  new Thread(() -> print("C",0));
        ThreadA.start();
        ThreadB.start();
        ThreadC.start();
    }
    private static void print(String s, int count) {
        for (int i = 0; i < 10; i++) {
            synchronized (lock) {
                while (state % 3 != count) {
                    try {
                        lock.wait();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                System.out.print(s);
                state++;
                lock.notifyAll();
            }
        }
    }
}
