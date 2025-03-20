package thread;

/**
 * @Description: wait和notifydemo
 * @Author:bread
 * @Date: 2024-12-03 21:18
 */
public class demo {
    public static void main(String[] args) {
        final Object lock = new Object();
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    try {
                        System.out.println("线程1等待");
                       lock.wait();
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("线程1被唤醒");
            }
        }
        ).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    try {
                        Thread.sleep(2000);
                        System.out.println("线程2等待2秒后唤醒线程1");
                        //lock.notify();
                        //System.out.println("成功唤醒线程1");
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        ).start();

        new Thread(()->{
            synchronized (lock){
                try {
                    System.out.println("线程3等待");
                    lock.wait();
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
