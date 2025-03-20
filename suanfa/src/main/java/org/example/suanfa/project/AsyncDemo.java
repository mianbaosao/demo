package org.example.suanfa.project;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 异步任务内嵌异步计算响应时长
 */
public class AsyncDemo {

    // 模拟一个线程池
    private static final ExecutorService executor = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {
        // 调用异步任务
        String result = updateUserBattleCards();
        System.out.println(result);
    }

    public static String updateUserBattleCards() {
        // 记录开始时间
        long startTime = System.currentTimeMillis();

        // 模拟表分区数量
        int tableCount = 5;
        List<CompletableFuture<Void>> futures = new ArrayList<>();

        // 启动外层异步任务
        for (int i = 0; i < tableCount; i++) {
            String tableName = "table_" + i;
            CompletableFuture<Void> future = CompletableFuture.runAsync(
                () -> {
                    // 内层异步任务
                    List<CompletableFuture<Void>> innerFutures = new ArrayList<>();
                    for (int j = 0; j < 3; j++) { // 每个外层任务启动 3 个内层任务
                        String taskName = tableName + "_task_" + j;
                        CompletableFuture<Void> innerFuture = CompletableFuture.runAsync(
                            () -> doSync(taskName),
                            executor
                        );
                        innerFutures.add(innerFuture);
                    }
                    // 等待内层任务完成
                    CompletableFuture.allOf(innerFutures.toArray(new CompletableFuture[0])).join();
                },
                executor
            );
            futures.add(future);
        }

        // 等待所有外层任务完成
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();

        // 记录耗时
        long endTime = System.currentTimeMillis();
        long duration = (endTime - startTime) / 1000; // 总耗时，单位秒
        System.out.println("所有任务完成，总耗时: " + duration + " 秒");

        return "success";
    }

    // 模拟异步任务
    public static void doSync(String taskName) {
        try {
            System.out.println("任务开始: " + taskName);
            // 模拟任务耗时
            TimeUnit.SECONDS.sleep(2);
            System.out.println("任务完成: " + taskName);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}