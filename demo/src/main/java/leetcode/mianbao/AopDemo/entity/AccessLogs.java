package leetcode.mianbao.AopDemo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * (AccessLogs)实体类
 *
 * @author makejava
 * @since 2024-11-30 17:35:34
 */
@Data
public class AccessLogs implements Serializable {
    private static final long serialVersionUID = -48107842523154498L;

    private Integer id;

    private Integer userId;

    private Integer contentId;

    private String contentType;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date visitTime;


    private String visitType;

    private String ipAddress;

    private String userAgent;

    private String referrer;


    public AccessLogs(Integer id, Integer userId, String contentType, Date visitTime, String visitType, String ipAddress, String userAgent, String referrer) {
        this.id = id;
        this.userId = userId;
        this.contentType = contentType;
        this.visitTime = visitTime;
        this.visitType = visitType;
        this.ipAddress = ipAddress;
        this.userAgent = userAgent;
        this.referrer = referrer;
    }

    // 无参构造函数
    public AccessLogs() {
    }

    @Override
    public String toString() {
        return "AccessLogs{" +
                "id=" + id +
                ", userId=" + userId +
                ", contentType='" + contentType + '\'' +
                ", visitTime=" + visitTime +
                ", visitType='" + visitType + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                ", userAgent='" + userAgent + '\'' +
                ", referrer='" + referrer + '\'' +
                '}';
    }

}

