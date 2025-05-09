package org.example.suanfa.project.buff.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
@NoArgsConstructor
public class BuffVO {

    private Integer lv;          // 当前等级（0级时不显示）
    private Integer maxLv;       // 最大等级（后台配置）
    private String icon;         // Buff图标URL
    private String title;        // Buff名称
    private Integer duration;    // 持续时间（秒）
    private String desc;         // 效果描述文案
    private Integer levelCost;   // 升到下一级所需道具数量
    private List<BuffStageConf> stageConf;  // 各等级效果配置
    private BuffStageConf stageConf1;
    // 动态计算字段（非持久化）
    private Boolean canUpgrade;  // 是否可升级（根据道具是否充足计算）
    private Boolean isMaxLevel;  // 是否已达满级

    public void setMaxLevel(Boolean maxLevel) {
        isMaxLevel = maxLevel;
    }

    public void setCanUpgrade(Boolean canUpgrade) {
        this.canUpgrade = canUpgrade;
    }

    public void setStageConf1(BuffStageConf stageConf1) {
        this.stageConf1 = stageConf1;
    }

    public void setStageConf(List<BuffStageConf> stageConf) {
        this.stageConf = stageConf;
    }

    public void setLevelCost(Integer levelCost) {
        this.levelCost = levelCost;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setMaxLv(Integer maxLv) {
        this.maxLv = maxLv;
    }

    public void setLv(Integer lv) {
        this.lv = lv;
    }

    @Data
    @Accessors(chain = true)
    public static class BuffStageConf {

        private Integer level;      // 等级
        private String effectDesc;  // 该等级效果描述

    }

}