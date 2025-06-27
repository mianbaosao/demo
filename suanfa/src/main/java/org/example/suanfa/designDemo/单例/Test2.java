package org.example.suanfa.designDemo.单例;

/**
 * @Description: 自测
 * @Author:bread
 * @Date: 2025-04-27 21:11
 */
public class Test2 {
    private static volatile Test2 instance;
    public Test2() {}
    public static Test2 getInstance(){
        if(instance == null){
            synchronized (Test2.class){
                if(instance == null){
                    instance = new Test2();
                }
            }
        }
        return instance;
    }
}
