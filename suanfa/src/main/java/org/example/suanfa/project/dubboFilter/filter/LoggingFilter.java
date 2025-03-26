package org.example.suanfa.project.dubboFilter.filter;

import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Activate(group = {CommonConstants.PROVIDER}, order = -2000)
public class LoggingFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(LoggingFilter.class);

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        String methodName = invocation.getMethodName();
        String interfaceName = invoker.getInterface().getName();
        
        logger.info("【LoggingFilter】收到调用请求 - 接口: {}, 方法: {}, 参数: {}", 
                interfaceName, methodName, invocation.getArguments());
        
        try {
            Result result = invoker.invoke(invocation);
            
            if (result.hasException()) {
                logger.error("【LoggingFilter】调用异常 - 接口: {}, 方法: {}, 异常: {}", 
                        interfaceName, methodName, result.getException().getMessage());
            } else {
                logger.info("【LoggingFilter】调用成功 - 接口: {}, 方法: {}, 结果类型: {}", 
                        interfaceName, methodName, result.getValue() != null ? result.getValue().getClass().getName() : "null");
            }
            
            return result;
        } catch (RpcException e) {
            logger.error("【LoggingFilter】调用失败 - 接口: {}, 方法: {}, 异常: {}", 
                    interfaceName, methodName, e.getMessage());
            throw e;
        }
    }
} 