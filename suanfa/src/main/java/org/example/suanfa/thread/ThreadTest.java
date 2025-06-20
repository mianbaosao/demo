package org.example.suanfa.thread;

/**
 * @author heweitao538 2025/6/20
 */
public class ThreadTest {
    public static void main(String[] args) {

        Thread threadA = new Thread(
                () -> {
                    for (int i = 0; i < 10; i++) {
                        System.out.println("A" + i);
                    }
                }
        );
//        threadA.run();
        CommonTaskThreadPool.submit(threadA);
    }
}
