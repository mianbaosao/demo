package org.example.suanfa.project.buff.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.example.suanfa.project.buff.dao.BuffConfigDao;
import org.example.suanfa.project.buff.dao.BuffLevelConfigDao;
import org.example.suanfa.project.buff.dao.UserBuffDao;
import org.example.suanfa.project.buff.entity.BuffConfig;
import org.example.suanfa.project.buff.entity.BuffLevelConfig;
import org.example.suanfa.project.buff.entity.UserBuff;
import org.example.suanfa.project.buff.service.BuffService;
import org.example.suanfa.project.buff.vo.BuffVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BuffServiceImpl implements BuffService {
    @Autowired
    private BuffConfigDao buffConfigDao;
    @Autowired
    private BuffLevelConfigDao buffLevelConfigDao;
    @Autowired
    private UserBuffDao userBuffDao;

    @Override
    public List<BuffVO> getUserBuffs(Long userId) {
        // 查询用户拥有的所有buff
        UserBuff query = new UserBuff();
        query.setUserId(userId);
        List<UserBuff> userBuffs = userBuffDao.queryAllByLimit(query);
        
        return userBuffs.stream().map(userBuff -> {
            // 获取buff基础配置
            BuffConfig buffConfig = buffConfigDao.queryById(userBuff.getBuffId());
            
            // 获取该buff的所有等级配置
            BuffLevelConfig levelQuery = new BuffLevelConfig();
            levelQuery.setBuffId(buffConfig.getId());
            List<BuffLevelConfig> levelConfigs = buffLevelConfigDao.queryAllByLimit(levelQuery);
            
            // 转换为VO对象
            BuffVO vo = new BuffVO();
            vo.setLv(userBuff.getCurrentLevel());
            vo.setMaxLv(buffConfig.getMaxLevel());
            vo.setIcon(buffConfig.getIcon());
            vo.setTitle(buffConfig.getName());
            vo.setDesc(buffConfig.getDescription());
            vo.setIsMaxLevel(userBuff.getCurrentLevel() >= buffConfig.getMaxLevel());
            
            // 设置各等级配置
            vo.setStageConfs(levelConfigs.stream()
                    .map(lc -> new BuffVO.BuffStageConf()
                            .setLevel(lc.getLevel())
                            .setEffectDesc(lc.getEffectDescription()))
                    .collect(Collectors.toList()));

            // 设置当前等级的持续时间
            if (userBuff.getCurrentLevel() > 0) {
                levelConfigs.stream()
                    .filter(lc -> lc.getLevel() == userBuff.getCurrentLevel())
                    .findFirst()
                    .ifPresent(lc -> vo.setDuration(lc.getDuration()));
            }
            
            // 检查是否可以升级
            if (!vo.getIsMaxLevel()) {
                levelConfigs.stream()
                    .filter(lc -> lc.getLevel() == userBuff.getCurrentLevel() + 1)
                    .findFirst()
                    .ifPresent(lc -> {
                        vo.setLevelCost(lc.getUpgradeCost());
                        vo.setCanUpgrade(true);
                    });
            } else {
                vo.setCanUpgrade(false);
            }
            
            return vo;
        }).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public BuffVO upgradeBuff(Long userId, Integer buffId) {
        // 1. 验证用户是否拥有该buff
        UserBuff query = new UserBuff();
        query.setUserId(userId);
        query.setBuffId(buffId);
        List<UserBuff> userBuffs = userBuffDao.queryAllByLimit(query);
        
        if (userBuffs.isEmpty()) {
            throw new RuntimeException("用户未拥有该Buff");
        }
        
        UserBuff userBuff = userBuffs.get(0);
        BuffConfig buffConfig = buffConfigDao.queryById(buffId);
        
        // 2. 检查是否已达最大等级
        if (userBuff.getCurrentLevel() >= buffConfig.getMaxLevel()) {
            throw new RuntimeException("Buff已达最大等级");
        }
        
        // 3. 获取下一级配置
        BuffLevelConfig levelQuery = new BuffLevelConfig();
        levelQuery.setBuffId(buffId);
        levelQuery.setLevel(userBuff.getCurrentLevel() + 1);
        List<BuffLevelConfig> nextLevels = buffLevelConfigDao.queryAllByLimit(levelQuery);
        
        if (nextLevels.isEmpty()) {
            throw new RuntimeException("找不到下一级配置");
        }
        
        BuffLevelConfig nextLevel = nextLevels.get(0);
        
        // 4. 检查升级消耗(这里简化处理，实际需要检查用户道具)
        if (nextLevel.getUpgradeCost() != null) {
            // TODO: 这里应该查询用户道具是否足够
            // int userItemCount = itemService.getItemCount(userId, ITEM_TYPE);
            // if (userItemCount < nextLevel.getUpgradeCost()) {
            //     throw new RuntimeException("道具不足");
            // }
            // itemService.consumeItem(userId, ITEM_TYPE, nextLevel.getUpgradeCost());
        }
        
        // 5. 更新用户Buff等级
        userBuff.setCurrentLevel(nextLevel.getLevel());
        if (nextLevel.getDuration() != null) {
            userBuff.setExpireTime(new Date(System.currentTimeMillis() + nextLevel.getDuration() * 1000L));
        }
        userBuffDao.update(userBuff);
        
        // 6. 返回更新后的Buff信息
        String buffTitle = buffConfig.getName();
        return getUserBuffs(userId).stream()
                .filter(vo -> buffTitle.equals(vo.getTitle()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("获取升级后Buff信息失败"));


    }
}