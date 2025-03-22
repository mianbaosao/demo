package org.example.suanfa.designDemo.单例;

/**
 * 饿汉式
 * 在类加载时期就创建实例
 */
public class SingletonEhan {
    // 在类加载时创建实例
    private static final SingletonEhan INSTANCE = new SingletonEhan();

    // 私有构造函数，防止外部实例化
    private SingletonEhan() {}

    // 提供全局访问点
    public static SingletonEhan getInstance() {
        return INSTANCE;
    }
}