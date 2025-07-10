package org.example.suanfa.designDemo.单例;



public class SingletonTest {
   private static volatile SingletonTest instance;
   public SingletonTest(){};
   public static SingletonTest getInstance(){
       if(instance == null){
           synchronized(SingletonTest.class){
               if(instance == null){
                   instance=new SingletonTest();
               }
           }
       }
       return instance;
   }
}
