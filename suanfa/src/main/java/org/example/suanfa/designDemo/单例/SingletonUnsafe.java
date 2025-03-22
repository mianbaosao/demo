package org.example.suanfa.designDemo.单例;

/**
 * 懒汉式线程不安全
 */
public class SingletonUnsafe {
    private static SingletonUnsafe instance;

    private SingletonUnsafe() {}

    public static SingletonUnsafe getInstance() {
        if (instance == null) {
            instance = new SingletonUnsafe();
        }
        return instance;
    }
}