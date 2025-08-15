package org.example.suanfa.project.kuaikan.async;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class CompletableFutureWithTimeoutDemo {
    public static void main(String[] args) {
        // 设置超时时间
        final long timeout = 3; // 3秒

        // 创建多个异步任务
        CompletableFuture<String> loadCharacter = CompletableFuture.supplyAsync(() -> {
            System.out.println("Loading character...");
            return loadCharacterData(); // 模拟加载角色数据
        });

        CompletableFuture<String> buildMonsterList = CompletableFuture.supplyAsync(() -> {
            System.out.println("Building monster list...");
            return buildMonsterListData(); // 模拟构建怪物列表
        });

        CompletableFuture<String> initializeActivity = CompletableFuture.supplyAsync(() -> {
            System.out.println("Initializing activity...");
            return initializeActivityData(); // 模拟初始化活动专题
        });

        // 使用 allOf 等待所有任务完成
        CompletableFuture<Void> allFutures = CompletableFuture.allOf(
                loadCharacter,
                buildMonsterList,
                initializeActivity
        );

        // 设置超时控制
        CompletableFuture<Void> timeoutFuture = CompletableFuture.runAsync(() -> {
            try {
                allFutures.get(timeout, TimeUnit.SECONDS); // 等待所有任务完成，超时时间为 3 秒
            } catch (TimeoutException e) {
                System.out.println("Timeout occurred. Cancelling all tasks...");
                loadCharacter.cancel(true);
                buildMonsterList.cancel(true);
                initializeActivity.cancel(true);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });

        // 等待超时控制任务完成
        try {
            timeoutFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        // 检查每个任务是否完成
        System.out.println("Load Character: " + loadCharacter.isDone());
        System.out.println("Build Monster List: " + buildMonsterList.isDone());
        System.out.println("Initialize Activity: " + initializeActivity.isDone());
    }

    // 模拟加载角色数据
    private static String loadCharacterData() {
        try {
            TimeUnit.SECONDS.sleep(1); // 模拟耗时操作
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return "Character Data";
    }

    // 模拟构建怪物列表
    private static String buildMonsterListData() {
        try {
            TimeUnit.SECONDS.sleep(2); // 模拟耗时操作
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return "Monster List Data";
    }

    // 模拟初始化活动专题
    private static String initializeActivityData() {
        try {
            TimeUnit.SECONDS.sleep(1); // 模拟耗时操作
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return "Activity Data";
    }
}