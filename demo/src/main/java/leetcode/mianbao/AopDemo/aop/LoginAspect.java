package leetcode.mianbao.AopDemo.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author:bread
 * @Date: 2024-11-04 22:38
 */
@Component
@Aspect
public class LoginAspect {

    @Pointcut("execution(public * leetcode.mianbao.AopDemo.controller.MyController.login(..))")
    private void loginPointCut() {
        // 这里不需要任何方法体
    }

    @Before("loginPointCut()")
    public void before(JoinPoint jp) {
        System.out.println("执行了登录功能");
        for(Object param:jp.getArgs()){
            System.out.println((String) param);
        }
    }

    @After("execution(public * leetcode.mianbao.AopDemo.controller.MyController.login(..))")
    public void after(JoinPoint joinPoint) {
        System.out.println("登录功能执行完毕");
    }

    //返回后通知,在方法正常返回后执行
    @AfterReturning(pointcut = "execution(public * leetcode.mianbao.AopDemo.controller.MyController.login(..))", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        System.out.println("登录功能返回结果: " + result);
    }

    //在方法抛出异常后执行
    @AfterThrowing(pointcut = "execution(public * leetcode.mianbao.AopDemo.controller.MyController.login(..))", throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint, Exception ex) {
        System.out.println("登录功能抛出异常: " + ex.getMessage());
    }
}