package org.example.suanfa.project.aop.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.example.suanfa.project.aop.res.RpcResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicLong;

/**
 * RpcResult切面类，用于统一处理错误码、请求数和耗时
 */
@Aspect
@Component
public class RpcResultAspect {

    // 日志记录器
    private static final Logger logger = LoggerFactory.getLogger(RpcResultAspect.class);

    // 请求计数器（线程安全）
    private static final AtomicLong requestCounter = new AtomicLong(0);

    /**
     * 环绕通知，拦截所有返回RpcResult的方法
     */
    @Around("execution(public org.example.suanfa.project.aop.res.RpcResult *(..))")
    public Object handleRpcResult(ProceedingJoinPoint joinPoint) throws Throwable {
        // 1. 记录开始时间
        long startTime = System.currentTimeMillis();

        // 2. 增加请求计数
        long requestCount = requestCounter.incrementAndGet();

        // 3. 获取请求参数（可选）
        Object[] args = joinPoint.getArgs();
        String methodName = joinPoint.getSignature().toShortString();

        // 记录请求日志（可选）
        logger.info("Method: {}, Request count: {}, Args: {}", methodName, requestCount, Arrays.toString(args));

        // 4. 执行目标方法
        Object result = joinPoint.proceed();

        // 5. 记录结束时间并计算耗时
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        // 6. 处理RpcResult
        if (result instanceof RpcResult) {
            RpcResult<?> rpcResult = (RpcResult<?>) result;

            // 统一处理错误码
            if (!rpcResult.isSuccess()) {
                logger.error("Method: {}, Error code: {}, Message: {}", 
                    methodName, rpcResult.getCode(), rpcResult.getMessage());
                // 可根据业务需求修改错误码或抛出异常，例如：
                // rpcResult.setCode(ResponseCodeMsg.UNIFIED_ERROR.getCode());
                // rpcResult.setMessage("Unified error handling: " + rpcResult.getMessage());
            }
        }

        // 7. 记录耗时
        logger.info("Method: {}, Execution time: {}ms", methodName, duration);

        // 8. 返回结果
        return result;
    }
}