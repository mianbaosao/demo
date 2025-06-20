package org.example.suanfa.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: bread @Date: 2025/3/21 12:47 @Description: 通用任务线程池
 */

@Slf4j
public class CommonTaskThreadPool {
    private static final int QUEUE_MAX_SIZE = 500;

    public static final ThreadPoolExecutor THREAD_POOL_EXECUTOR =
            new ThreadPoolExecutor(
                    10,
                    50,
                    30L,
                    TimeUnit.SECONDS,
                    new LinkedBlockingQueue<>(QUEUE_MAX_SIZE),
                    new ThreadFactory() {
                        private final AtomicInteger count = new AtomicInteger(0);

                        @Override
                        public Thread newThread(Runnable r) {
                            Thread t = new Thread(r);
                            String threadName = "task-thread-" + count.addAndGet(1);
                            t.setName(threadName);
                            return t;
                        }
                    },
                    new ThreadPoolExecutor.AbortPolicy());

    public static Future<?> submit(Callable<?> task) {
        return THREAD_POOL_EXECUTOR.submit(task);
    }

    public static void submit(Runnable runnable) {
        THREAD_POOL_EXECUTOR.submit(runnable);
    }

    public static void submit(Runnable runnable, Integer caseId) {
        THREAD_POOL_EXECUTOR.submit(runnable);
        if (caseId != null) {
            return;
        }
        log.error("[CommonTaskThreadPool] submit task thread pool error, caseId is null");
    }
}
