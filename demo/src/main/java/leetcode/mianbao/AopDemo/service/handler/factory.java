package leetcode.mianbao.AopDemo.service.handler;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 工厂
 * @Author:bread
 * @Date: 2024-11-06 20:19
 */
@Component
public class factory implements InitializingBean {

    @Resource
    private List<loginTypeHandler>loginTypeHandlers;

    private Map<loginEnum,loginTypeHandler> handlerMap=new HashMap<>();


    public loginTypeHandler getHandler(int codeval){
        loginEnum loginEnum= leetcode.mianbao.AopDemo.service.handler.loginEnum.getByCode(codeval);
        return handlerMap.get(loginEnum);
    }
    @Override
    public void afterPropertiesSet() throws Exception {
        for(loginTypeHandler loginTypeHandler:loginTypeHandlers){
            handlerMap.put(loginTypeHandler.getHandlerType(),loginTypeHandler);
        }
    }
}
