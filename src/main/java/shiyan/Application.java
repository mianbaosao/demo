package shiyan;

import shiyan.IUserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication(scanBasePackages = "shiyan")
public class Application {
    public static void main(String[] args) {
        // 启动 Spring Boot 应用
        ApplicationContext context = SpringApplication.run(Application.class, args);

        // 获取 UserService Bean
        IUserService userService = context.getBean(IUserService.class);
        
        // 调用 register 方法，触发 AOP
        userService.register("John", "123456");
    }
}
