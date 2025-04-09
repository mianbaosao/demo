package org.example.suanfa.project.buff.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * Buff基础配置表(BuffConfig)实体类
 *
 * @author makejava
 * @since 2025-04-09 15:55:45
 */
public class BuffConfig implements Serializable {
    private static final long serialVersionUID = -48137432192842760L;
/**
     * Buff ID
     */
    private Integer id;
/**
     * Buff名称
     */
    private String name;
/**
     * 图标资源路径
     */
    private String icon;
/**
     * Buff描述
     */
    private String description;
/**
     * 最大等级
     */
    private Integer maxLevel;
/**
     * 是否启用
     */
    private Integer isActive;

    private Date createdAt;

    private Date updatedAt;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getMaxLevel() {
        return maxLevel;
    }

    public void setMaxLevel(Integer maxLevel) {
        this.maxLevel = maxLevel;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

}

