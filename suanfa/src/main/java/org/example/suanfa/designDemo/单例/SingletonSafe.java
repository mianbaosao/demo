package org.example.suanfa.designDemo.单例;

/**
 * 懒汉式线程安全
 */
public class SingletonSafe {
    private static SingletonSafe instance;

    private SingletonSafe() {}

    public static synchronized SingletonSafe getInstance() {
        if (instance == null) {
            instance = new SingletonSafe();
        }
        return instance;
    }
}