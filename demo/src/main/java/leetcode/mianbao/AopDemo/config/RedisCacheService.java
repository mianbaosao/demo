package leetcode.mianbao.AopDemo.config;

/**
 * @Description:
 * @Author:bread
 * @Date: 2024-11-30 19:03
 */
import leetcode.mianbao.AopDemo.entity.AccessLogs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class RedisCacheService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    private static final String CACHE_KEY = "accessLogs"; // Redis 缓存的 key

    // 将 AccessLogs 数据保存到 Redis
    public void cacheAccessLogsList(String key, List<AccessLogs> accessLogsList) {
        // 序列化对象并将其存入 Redis（简化示例，实际项目中可使用 JSON 序列化）
        redisTemplate.opsForValue().set(key, accessLogsList.toString());
    }

    // 从 Redis 获取 AccessLogs 数据
    public List<AccessLogs> getAccessLogsFromRedis(String key) {
        // 从 Redis 中获取数据
        String cachedData = redisTemplate.opsForValue().get(key);
        if (cachedData != null) {
            // 此处简化处理，你可以根据需要进行反序列化为 AccessLogs 对象
            return Arrays.asList(); // 返回序列化的对象列表
        }
        return null; // 如果 Redis 没有数据，返回 null
    }
}

