package shiyan;

import org.aspectj.lang.ProceedingJoinPoint;

public class LogAspect {

    public void beforeRegister() {
        System.out.println("[XML AOP] 前置通知：方法 register() 被调用前");
    }

    public void afterReturningRegister() {
        System.out.println("[XML AOP] 后置通知：方法 register() 正常返回");
    }

    public void afterRegister() {
        System.out.println("[XML AOP] 最终通知：方法 register() 执行完毕");
    }

    public void afterThrowingRegister() {
        System.out.println("[XML AOP] 异常通知：方法 register() 发生异常");
    }

    public Object aroundRegister(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("[XML AOP] 环绕通知：方法 register() 执行前");
        Object result = pjp.proceed();
        System.out.println("[XML AOP] 环绕通知：方法 register() 执行后");
        return result;
    }
}
