package leetcode.mianbao.AopDemo.dao;

import leetcode.mianbao.AopDemo.entity.AccessLogs;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Description:
 * @Author:bread
 * @Date: 2024-11-30 18:31
 */
@Mapper
public interface StressDao {
    @Select("select * from access_logs")
     List<AccessLogs> getStress();


    // 批量插入 AccessLogs 数据
    @Insert("<script>" +
            "INSERT INTO access_logs (user_id, content_id, content_type, visit_time, visit_type, ip_address, user_agent, referrer) " +
            "VALUES " +
            "<foreach collection='logs' item='log' index='index' separator=','>" +
            "(#{log.userId}, #{log.contentId}, #{log.contentType}, #{log.visitTime}, #{log.visitType}, #{log.ipAddress}, #{log.userAgent}, #{log.referrer})" +
            "</foreach>" +
            "</script>")
    void insertAccessLogs(@Param("logs") List<AccessLogs> logs);
}
