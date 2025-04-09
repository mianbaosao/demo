package org.example.suanfa.project.buff.entity;

import java.io.Serializable;

/**
 * Buff等级配置表(BuffLevelConfig)实体类
 *
 * @author makejava
 * @since 2025-04-09 15:56:36
 */
public class BuffLevelConfig implements Serializable {
    private static final long serialVersionUID = -75327393701639688L;

    private Integer id;
/**
     * 关联buff_config.id
     */
    private Integer buffId;
/**
     * 等级
     */
    private Integer level;
/**
     * 效果值(JSON存储具体效果)
     */
    private String effectValue;
/**
     * 效果描述
     */
    private String effectDescription;
/**
     * 升级消耗
     */
    private Integer upgradeCost;
/**
     * 持续时间(秒)
     */
    private Integer duration;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBuffId() {
        return buffId;
    }

    public void setBuffId(Integer buffId) {
        this.buffId = buffId;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getEffectValue() {
        return effectValue;
    }

    public void setEffectValue(String effectValue) {
        this.effectValue = effectValue;
    }

    public String getEffectDescription() {
        return effectDescription;
    }

    public void setEffectDescription(String effectDescription) {
        this.effectDescription = effectDescription;
    }

    public Integer getUpgradeCost() {
        return upgradeCost;
    }

    public void setUpgradeCost(Integer upgradeCost) {
        this.upgradeCost = upgradeCost;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

}

