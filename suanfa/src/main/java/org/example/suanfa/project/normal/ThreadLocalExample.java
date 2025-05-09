package org.example.suanfa.project.normal;

public class ThreadLocalExample {

    // 创建一个ThreadLocal实例
    public static final ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        // 在主线程中设置值
        threadLocal.set("主线程的值");

        // 在主线程中获取值
        System.out.println("主线程的值：" + threadLocal.get());

        // 创建一个新的线程，并在其中设置和获取值
        Thread thread = new Thread(() -> {
            threadLocal.set("子线程的值");
            System.out.println("子线程的值：" + threadLocal.get());
        });

        thread.start();
    }
}
