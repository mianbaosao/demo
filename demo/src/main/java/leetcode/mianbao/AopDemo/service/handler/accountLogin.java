package leetcode.mianbao.AopDemo.service.handler;

import org.springframework.stereotype.Component;

/**
 * @Description: 账号
 * @Author:bread
 * @Date: 2024-11-06 20:14
 */
@Component
public class accountLogin implements loginTypeHandler{


    @Override
    public loginEnum getHandlerType() {
        return loginEnum.ACOUNT;
    }

    @Override
    public String test() {
        return "这是账号登录";
    }
}
