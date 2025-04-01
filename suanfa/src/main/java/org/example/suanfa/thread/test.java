package org.example.suanfa.thread;

import java.util.HashMap;

public class test {
   private static Object lock = new Object();
   private static int state=0;
   public static void main(String[] args) {
       Thread a=new Thread(()->print("A",0));
       Thread b=new Thread(()->print("B",1));
       Thread c=new Thread(()->print("C",2));

       a.start();
       b.start();
       c.start();
   }

    private static void print(String a, int i) {
        for (int i1 = 0; i1 < 10; i1++) {
            synchronized (lock) {
                while (state % 3 != i) {
                    try {
                        lock.wait();
                    } catch (Exception e) {
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
