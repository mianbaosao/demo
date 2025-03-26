package org.example.suanfa.project.dubboFilter.service;


import org.apache.dubbo.config.annotation.DubboService;
import org.example.suanfa.project.dubboFilter.res.RpcResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@DubboService(version = "1.0.0")
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    
    @Override
    public RpcResult getUser(String id) {
        logger.info("==== 接收到获取用户请求: id={} ====", id);
        RpcResult result = new RpcResult();
        result.setCode(200);
        result.setMessage("User: " + id);
        logger.info("==== 返回用户结果: code={}, message={} ====", result.getCode(), result.getMessage());
        return result;
    }
}