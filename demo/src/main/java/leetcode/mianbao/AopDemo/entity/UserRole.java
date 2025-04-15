package leetcode.mianbao.AopDemo.entity;

import java.io.Serializable;

/**
 * (UserRole)实体类
 *
 * @author makejava
 * @since 2025-04-11 16:18:11
 */
public class UserRole implements Serializable {
    private static final long serialVersionUID = 135248921137836850L;

    private Long userId;

    private Long roleId;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

}

