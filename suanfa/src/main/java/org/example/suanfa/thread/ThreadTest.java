package org.example.suanfa.thread;

/**
 * @author heweitao538 2025/6/20
 */
public class ThreadTest {
    private static final Object lock =new Object();
    private static int state=0;

    public static void main(String[] args) {
        Thread threadA=new Thread(()->print("A",0));
        Thread threadB=new Thread(()->print("B",1));
        Thread threadC=new Thread(()->print("C",2));
        threadA.start();
        threadB.start();
        threadC.start();
    }

    private static void print(String a, int num) {
        for (int i = 0; i < 10; i++) {
            synchronized(lock){
                while(state%3!=num){
                    try {
                        lock.wait();
                    }catch (Exception e){
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
