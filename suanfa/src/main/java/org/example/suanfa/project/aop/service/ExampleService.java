package org.example.suanfa.project.aop.service;

import org.example.suanfa.project.aop.res.RpcResult;

public interface ExampleService {
    RpcResult<String> getSuccessResult();
    RpcResult<String> getErrorResult();
    RpcResult<String> getResultWithParam(String param);
}