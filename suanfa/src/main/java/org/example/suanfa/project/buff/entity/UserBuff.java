package org.example.suanfa.project.buff.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * 用户Buff状态表(UserBuff)实体类
 *
 * @author makejava
 * @since 2025-04-09 15:56:53
 */
public class UserBuff implements Serializable {
    private static final long serialVersionUID = -86090303216172275L;

    private Long id;
/**
     * 用户ID
     */
    private Long userId;
/**
     * Buff ID
     */
    private Integer buffId;
/**
     * 当前等级
     */
    private Integer currentLevel;
/**
     * 过期时间
     */
    private Date expireTime;
/**
     * 是否启用
     */
    private Integer isActive;

    private Date createdAt;

    private Date updatedAt;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getBuffId() {
        return buffId;
    }

    public void setBuffId(Integer buffId) {
        this.buffId = buffId;
    }

    public Integer getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(Integer currentLevel) {
        this.currentLevel = currentLevel;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
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

