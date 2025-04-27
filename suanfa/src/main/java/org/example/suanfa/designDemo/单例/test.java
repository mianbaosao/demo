package org.example.suanfa.designDemo.单例;

/**
 * 单例测试
 */
public class test {
    public static volatile test instance;
    public test() {}
    public static test getInstance() {
        if(instance == null) {
            synchronized(test.class){
                if(instance == null) {
                    instance = new test();
                }
            }
        }
        return instance;
    }
}
