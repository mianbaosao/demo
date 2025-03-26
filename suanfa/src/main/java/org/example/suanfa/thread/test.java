package org.example.suanfa.thread;

public class test {
    private static final Object lock = new Object();
    private static int state = 0;

    public static void main(String[] args) {
        Thread t1=new Thread(()->print("A",0));
        Thread t2=new Thread(()->print("B",1));
        Thread t3=new Thread(()->print("C",2));
        t1.start();
        t2.start();
        t3.start();
    }

    private static void print(String a, int cnt) {
        for (int i = 0; i < 10; i++) {
            synchronized(lock){
                while (state % 3!=cnt) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.print(a);
                state++;
                lock.notifyAll();
            }
        }
    }
}
