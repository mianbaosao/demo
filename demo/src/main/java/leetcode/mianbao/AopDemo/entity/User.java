package leetcode.mianbao.AopDemo.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * (User)实体类
 *
 * @author makejava
 * @since 2025-04-11 16:18:09
 */
@Data
public class User implements Serializable {
    private static final long serialVersionUID = 661289255656379293L;

    private Long id;

    private String username;

    private String password;

    private String email;

    private Date createTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}

