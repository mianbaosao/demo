package org.example.suanfa.project.buff.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.suanfa.project.buff.entity.BuffConfig;

/**
 * Buff基础配置表(BuffConfig)表数据库访问层
 *
 * @author makejava
 * @since 2025-04-09 15:55:44
 */
@Mapper
public interface BuffConfigDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    BuffConfig queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param buffConfig 查询条件
     * @return 对象列表
     */
    List<BuffConfig> queryAllByLimit(BuffConfig buffConfig);

    /**
     * 统计总行数
     *
     * @param buffConfig 查询条件
     * @return 总行数
     */
    long count(BuffConfig buffConfig);

    /**
     * 新增数据
     *
     * @param buffConfig 实例对象
     * @return 影响行数
     */
    int insert(BuffConfig buffConfig);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<BuffConfig> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<BuffConfig> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<BuffConfig> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<BuffConfig> entities);

    /**
     * 修改数据
     *
     * @param buffConfig 实例对象
     * @return 影响行数
     */
    int update(BuffConfig buffConfig);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}

