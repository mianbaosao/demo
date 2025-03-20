package leetcode.mianbao.AopDemo.service;



/**
 * @Description: 登录接口
 * @Author:bread
 * @Date: 2024-11-06 20:11
 */

public interface userService {
    String factory(int type);

    void insertMultipleAccessLogs();
}
