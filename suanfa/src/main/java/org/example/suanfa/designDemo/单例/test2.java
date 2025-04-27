package org.example.suanfa.designDemo.单例;

/**
 * @Description: 自测
 * @Author:bread
 * @Date: 2025-04-27 21:11
 */
public class test2 {
    private static volatile test2 instance;
    public test2(){}
    public static test2 getInstance(){
        if (instance==null){
            synchronized (test.class){
                if(instance==null){
                    instance=new test2();
                }
            }
        }
        return instance;
    }
}
