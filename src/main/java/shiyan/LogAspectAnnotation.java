package shiyan;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspectAnnotation {

    @Before("execution(* shiyan.IUserService.register(..))")
    public void beforeRegister() {
        System.out.println("[注解 AOP] 前置通知：方法 register() 被调用前");
    }

    @AfterReturning("execution(* shiyan.IUserService.register(..))")
    public void afterReturningRegister() {
        System.out.println("[注解 AOP] 后置通知：方法 register() 正常返回");
    }

    @After("execution(* shiyan.IUserService.register(..))")
    public void afterRegister() {
        System.out.println("[注解 AOP] 最终通知：方法 register() 执行完毕");
    }

    @AfterThrowing("execution(* shiyan.IUserService.register(..))")
    public void afterThrowingRegister() {
        System.out.println("[注解 AOP] 异常通知：方法 register() 发生异常");
    }

    @Around("execution(* shiyan.IUserService.register(..))")
    public Object aroundRegister(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("[注解 AOP] 环绕通知：方法 register() 执行前");
        Object result = pjp.proceed();
        System.out.println("[注解 AOP] 环绕通知：方法 register() 执行后");
        return result;
    }
}
