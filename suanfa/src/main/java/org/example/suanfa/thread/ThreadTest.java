package org.example.suanfa.thread;

/**
 * @author heweitao538 2025/6/20
 */
public class ThreadTest {
   private static final Object lock = new Object();
   public static int cnt=0;

    public static void main(String[] args) {
        Thread A=new Thread(()->print("A",0));
        Thread B=new Thread(()->print("B",1));
        Thread C=new Thread(()->print("C",2));
        A.start();
        B.start();
        C.start();
    }

    private static void print(String a, int b) {
        for (int i = 0; i < 10; i++) {
            synchronized(lock){
                while(cnt%3!=b){
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.print(a);
                cnt++;
                lock.notifyAll();
            }
        }
    }
}
