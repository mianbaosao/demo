package leetcode.mianbao.AopDemo.service.impl;

import leetcode.mianbao.AopDemo.dao.StressDao;
import leetcode.mianbao.AopDemo.entity.AccessLogs;
import leetcode.mianbao.AopDemo.service.handler.factory;
import leetcode.mianbao.AopDemo.service.handler.loginTypeHandler;
import leetcode.mianbao.AopDemo.service.userService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description: 实现类
 * @Author:bread
 * @Date: 2024-11-06 20:11
 */
@Service
public class userServiceImpl implements userService {

    @Resource
    private factory factorty;

    @Resource
    private StressDao stressDao;

    public userServiceImpl() throws ParseException {
    }

    @Override
    public String factory(int type) {
        loginTypeHandler handler = factorty.getHandler(type);
        return handler.test();
    }

    String dateString = "2023-11-30"; // 目标日期
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    Date date = sdf.parse(dateString); // 将字符串转为 Date 对象
    @Override
    // 批量插入 10000 条数据
    public void insertMultipleAccessLogs() {
        List<AccessLogs> logs = new ArrayList<>();

        for (int i = 1; i <= 10000; i++) {
            AccessLogs log = new AccessLogs();
            log.setUserId(i);  // 假设 userId 为 i
            log.setContentId(i * 10);  // 假设 contentId 为 i*10
            log.setContentType("contentType" + i);
            log.setVisitTime(date);  // 设置当前时间为 visitTime
            log.setVisitType("visitType" + i);
            log.setIpAddress("192.168.0.1");
            log.setUserAgent("Mozilla");
            log.setReferrer("referrer" + i);
            logs.add(log);
        }

        // 调用 DAO 批量插入
        stressDao.insertAccessLogs(logs);
    }
}
