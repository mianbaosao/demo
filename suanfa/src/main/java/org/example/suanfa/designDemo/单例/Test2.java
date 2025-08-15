package org.example.suanfa.designDemo.单例;

/**
 * @Description: 自测
 * @Author:bread
 * @Date: 2025-04-27 21:11
 */
public class Test2 {
    private static volatile Test2 instanse;
    public Test2(){}
    public static Test2 getInstance(){
        if(instanse==null){
            synchronized(Test2.class){
                if(instanse==null){
                    instanse=new Test2();
                }
            }
        }
        return instanse;
    }
}
