package org.example.suanfa.project.kuaikan.async;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureDemo {
    public static void main(String[] args) {
        // 创建两个异步任务
        CompletableFuture<Integer> task1 = CompletableFuture.supplyAsync(() -> {
            // 模拟耗时任务
            System.out.println("Task 1 is running on " + Thread.currentThread().getName());
            return computeSomething(1);
        });

        CompletableFuture<Integer> task2 = CompletableFuture.supplyAsync(() -> {
            // 模拟耗时任务
            System.out.println("Task 2 is running on " + Thread.currentThread().getName());
            return computeSomething(2);
        });

        // 当两个任务都完成时，合并结果
        CompletableFuture<Integer> combinedFuture = task1.thenCombine(task2, (result1, result2) -> result1 + result2);

        // 获取最终结果
        try {
            int result = combinedFuture.get(); // 等待两个任务完成并获取结果
            System.out.println("Combined result is: " + result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    // 模拟耗时计算
    private static int computeSomething(int input) {
        try {
            Thread.sleep(1000); // 模拟耗时操作
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return input * input;
    }
}