package leetcode.mianbao.AopDemo.service.handler;

import org.springframework.stereotype.Component;

/**
 * @Description: qq登录
 * @Author:bread
 * @Date: 2024-11-06 20:13
 */
@Component
public class qqLogin implements loginTypeHandler{
    @Override
    public loginEnum getHandlerType() {
        return loginEnum.QQ;
    }

    @Override
    public String test() {
        return "这是企鹅登录";
    }
}
