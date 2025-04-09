package org.example.suanfa.project.buff.vo;

import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class BuffVO {

    private Integer lv;          // 当前等级（0级时不显示）
    private Integer maxLv;       // 最大等级（后台配置）
    private String icon;         // Buff图标URL
    private String title;        // Buff名称
    private Integer duration;    // 持续时间（秒）
    private String desc;         // 效果描述文案
    private Integer levelCost;   // 升到下一级所需道具数量
    private List<BuffStageConf> stageConfs;  // 各等级效果配置

    // 动态计算字段（非持久化）
    private Boolean canUpgrade;  // 是否可升级（根据道具是否充足计算）
    private Boolean isMaxLevel;  // 是否已达满级

    @Data
    @Accessors(chain = true)
    public static class BuffStageConf {

        private Integer level;      // 等级
        private String effectDesc;  // 该等级效果描述

    }
}