package leetcode.mianbao.AopDemo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import leetcode.mianbao.AopDemo.config.RedisCacheService;
import leetcode.mianbao.AopDemo.dao.StressDao;
import leetcode.mianbao.AopDemo.entity.AccessLogs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.google.common.cache.Cache;
import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description: 
 * @Author:bread
 * @Date: 2024-11-30 18:34
 */
@RestController
public class StressController {
    @Resource
    private StressDao stressDao;


    @Resource
    private RedisCacheService redisCacheService;

    @Resource
    private Cache<Integer, AccessLogs> accessLogsCache; // 本地缓存

    @Autowired
    private StringRedisTemplate redisTemplate; // Redis

    @Autowired
    private ObjectMapper objectMapper;


    public StressController() throws ParseException {
    }

    @GetMapping("/showDatabase")
    public List<AccessLogs> get(){
        return stressDao.getStress();
    }

    @GetMapping("/showCache")
    public List<AccessLogs> getAllAccessLogs() {
        List<AccessLogs> logs = new ArrayList<>();

        // 遍历本地缓存中的所有记录
        for (Integer key : accessLogsCache.asMap().keySet()) { // 假设 accessLogsCache 是 Guava 缓存
            AccessLogs log = accessLogsCache.getIfPresent(key); // 从本地缓存获取数据
            if (log != null) {
                logs.add(log);
            }
        }
        return logs;
    }



    String dateString = "2023-11-30"; // 目标日期
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    Date date = sdf.parse(dateString); // 将字符串转为 Date 对象
    @GetMapping("/insert")
    public String insert() {
        // 插入20000条数据到本地缓存
        for (int i = 1; i <= 20000; i++) {
            // 创建自定义的 AccessLogs 对象
            AccessLogs log = new AccessLogs(i, i * 10, "contentType" + i, null, "visitType" + i,
                    "192.168.0.1", "Mozilla", "referrer" + i);
            // 插入到本地缓存
            accessLogsCache.put(i, log);
        }

        return "20000条数据已经成功插入到本地缓存中";
    }

}
