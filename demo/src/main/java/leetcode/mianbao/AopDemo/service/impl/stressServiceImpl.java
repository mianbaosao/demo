package leetcode.mianbao.AopDemo.service.impl;

import leetcode.mianbao.AopDemo.dao.StressDao;
import leetcode.mianbao.AopDemo.entity.AccessLogs;
import leetcode.mianbao.AopDemo.service.stressService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @Author:bread
 * @Date: 2024-11-30 18:26
 */
@Service
public class stressServiceImpl implements stressService {
    @Resource
    private StressDao stressDao;

    @Override
    public List<AccessLogs> getStress() {
        return stressDao.getStress();
    }
}
