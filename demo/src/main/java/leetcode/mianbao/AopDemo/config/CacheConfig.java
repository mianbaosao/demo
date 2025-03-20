package leetcode.mianbao.AopDemo.config;

/**
 * @Description:
 * @Author:bread
 * @Date: 2024-11-30 19:02
 */
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import leetcode.mianbao.AopDemo.entity.AccessLogs;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class CacheConfig {

    // Guava本地缓存
    @Bean
    public Cache<Integer, AccessLogs> accessLogsCache() {
        return CacheBuilder.newBuilder()
                .maximumSize(20000) // 最大缓存100条数据
                .expireAfterWrite(1, TimeUnit.HOURS) // 数据写入1day后过期
                .build();
    }
}
