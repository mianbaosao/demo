package leetcode.mianbao.AopDemo.service.handler;

import org.springframework.stereotype.Component;

/**
 * @Description: 微信登录
 * @Author:bread
 * @Date: 2024-11-06 20:13
 */
@Component
public class wxLogin implements loginTypeHandler{
    @Override
    public loginEnum getHandlerType() {
        return loginEnum.WX;
    }

    @Override
    public String test() {
        return "这是微信登录";
    }
}
