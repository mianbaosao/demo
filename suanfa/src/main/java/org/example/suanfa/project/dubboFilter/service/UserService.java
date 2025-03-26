package org.example.suanfa.project.dubboFilter.service;

import org.example.suanfa.project.dubboFilter.res.RpcResult;

public interface UserService {
    RpcResult getUser(String id);
}