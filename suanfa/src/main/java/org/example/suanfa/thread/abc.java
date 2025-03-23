package org.example.suanfa.thread;

public class abc {
    private static final Object LOCK = new Object();
    private static  Integer cnt=0;
    public static void main(String[] args) {
        Thread threadA = new Thread(() -> print("A",0));
        Thread threadB = new Thread(() -> print("B",1));
        Thread threadC = new Thread(() -> print("C",2));
        threadA.start();
        threadB.start();
        threadC.start();
    }

    public static void print(String s, Integer i) {
        for (int i1 = 0; i1 < 10; i1++) {
            synchronized (LOCK) {
                while (cnt % 3 != i) {
                    try {
                        LOCK.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.print(s);
                cnt++;
                LOCK.notifyAll();
            }
        }
    }
}
