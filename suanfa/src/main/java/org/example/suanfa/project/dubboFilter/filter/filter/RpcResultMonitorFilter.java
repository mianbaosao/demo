package org.example.suanfa.project.dubboFilter.filter.filter;


import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;
import org.example.suanfa.project.dubboFilter.res.RpcResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Activate(group = {CommonConstants.PROVIDER}, order = -1000)
public class RpcResultMonitorFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(RpcResultMonitorFilter.class);

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        logger.info("进入过滤器: RpcResultMonitorFilter, 方法: {}", invocation.getMethodName());
        long startTime = System.currentTimeMillis();
        Result result = invoker.invoke(invocation);
        long durationTime = System.currentTimeMillis() - startTime;
        String methodName = invocation.getMethodName();
        String interfaceName = invoker.getInterface().getName();

        // 正确处理返回结果
        if (result.getValue() != null) {
            Object value = result.getValue();
            if (value instanceof RpcResult) {
                RpcResult rpcResult = (RpcResult) value;
                logger.info("拦截到 RpcResult 调用: interface={}, method={}, duration={}ms, code={}, message={}",
                        interfaceName, methodName, durationTime, rpcResult.getCode(), rpcResult.getMessage());
            } else {
                // 记录非RpcResult类型的返回值
                logger.info("拦截到调用: interface={}, method={}, duration={}ms, resultType={}",
                        interfaceName, methodName, durationTime, value.getClass().getName());
            }
        } else {
            // 处理空返回值的情况
            logger.info("拦截到调用: interface={}, method={}, duration={}ms, 返回值为null",
                    interfaceName, methodName, durationTime);
        }
        
        logger.info("离开过滤器: RpcResultMonitorFilter");
        return result;
    }
}