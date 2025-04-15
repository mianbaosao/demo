package org.example.suanfa.project.buff.entity;

import java.util.Date;
import java.io.Serializable;

import lombok.Data;

/**
 * 用户Buff状态表(UserBuff)实体类
 *
 * @author makejava
 * @since 2025-04-09 15:56:53
 */
@Data
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


}

