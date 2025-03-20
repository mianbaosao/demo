package leetcode.mianbao.AopDemo.controller;

import leetcode.mianbao.AopDemo.service.userService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Description:
 * @Author:bread
 * @Date: 2024-11-04 22:29
 */
@RestController
@CrossOrigin("*")
public class MyController {
    @Resource
    private userService userService;


    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password){
        if("admin".equals(username)&&"12345".equals(password))
            return "登录成功";
        else
            return "false";
    }

    @RequestMapping("/factory")
    public String factory(@RequestParam int type){
        return userService.factory(type);
    }

    @GetMapping("/insertBatch")
    public ResponseEntity<String> insertBatch() {
        userService.insertMultipleAccessLogs();
        return ResponseEntity.ok("Successfully inserted 10000 records.");
    }
}
