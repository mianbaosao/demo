package org.example.suanfa.project.aop.controller;

import org.example.suanfa.project.aop.res.RpcResult;
import org.example.suanfa.project.aop.service.ExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampleController {

    @Autowired
    private ExampleService exampleService;

    @GetMapping("/getSuccess")
    public RpcResult<String> getSuccess() {
        return exampleService.getSuccessResult();
    }

    @GetMapping("/error")
    public RpcResult<String> getError() {
        return exampleService.getErrorResult();
    }
    @GetMapping("/test")
    public String tset(){
        return "test";
    }

    @GetMapping("/param")
    public RpcResult<String> getResultWithParam(@RequestParam String param) {
        return exampleService.getResultWithParam(param);
    }
}