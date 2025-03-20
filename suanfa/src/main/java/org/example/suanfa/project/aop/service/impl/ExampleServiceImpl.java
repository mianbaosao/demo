package org.example.suanfa.project.aop.service.impl;

import org.example.suanfa.project.aop.res.RpcResult;
import org.example.suanfa.project.aop.service.ExampleService;
import org.springframework.stereotype.Service;

@Service
public class ExampleServiceImpl implements ExampleService {

    @Override
    public RpcResult<String> getSuccessResult() {
        // 模拟成功业务逻辑
        return RpcResult.success("Hello, World!");
    }

    @Override
    public RpcResult<String> getErrorResult() {
        // 模拟失败业务逻辑
        return RpcResult.result(-1, "Error occurred");
    }

    @Override
    public RpcResult<String> getResultWithParam(String param) {
        // 根据参数返回不同结果
        if ("success".equals(param)) {
            return RpcResult.success("Success with param: " + param);
        } else {
            return RpcResult.result(-1, "Error with param: " + param);
        }
    }
}