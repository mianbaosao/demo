package org.example.suanfa.leetcode.dp;

/**
 * @Description:
 * @Author:bread
 * @Date: 2025-02-17 11:53
 */
public class thread {
    private static final Object lock = new Object();
    private static int curId = 0;

    static class test implements Runnable {

        private int id;
        private char output;

        public test(int id, char output) {
            this.id = id;
            this.output = output;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                synchronized (lock) {
                    while (curId != id) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                    System.out.print(output);

                    curId = (curId + 1) % 3;
                    lock.notifyAll();
                }

            }
        }
    }


    public static void main(String[] args) {
        Thread threadA = new Thread(new test(0, 'A'));
        Thread threadB = new Thread(new test(1, 'B'));
        Thread threadC = new Thread(new test(2, 'C'));

        threadA.start();
        threadB.start();
        threadC.start();
    }
}
