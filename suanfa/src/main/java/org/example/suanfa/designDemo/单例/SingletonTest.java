package org.example.suanfa.designDemo.单例;



public class SingletonTest {
        private static volatile SingletonTest INSTANCE;
        private SingletonTest() {}
    public static SingletonTest getInstance() {
            if (INSTANCE == null) {
                synchronized (SingletonTest.class) {
                    if (INSTANCE == null) {
                        INSTANCE = new SingletonTest();
                    }
                }
            }
            return INSTANCE;
    }

}
