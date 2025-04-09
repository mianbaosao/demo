package org.example.suanfa.project.buff.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.suanfa.project.buff.entity.BuffLevelConfig;

/**
 * Buff等级配置表(BuffLevelConfig)表数据库访问层
 *
 * @author makejava
 * @since 2025-04-09 15:56:36
 */
@Mapper
public interface BuffLevelConfigDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    BuffLevelConfig queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param buffLevelConfig 查询条件
     * @return 对象列表
     */
    List<BuffLevelConfig> queryAllByLimit(BuffLevelConfig buffLevelConfig);

    /**
     * 统计总行数
     *
     * @param buffLevelConfig 查询条件
     * @return 总行数
     */
    long count(BuffLevelConfig buffLevelConfig);

    /**
     * 新增数据
     *
     * @param buffLevelConfig 实例对象
     * @return 影响行数
     */
    int insert(BuffLevelConfig buffLevelConfig);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<BuffLevelConfig> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<BuffLevelConfig> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<BuffLevelConfig> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<BuffLevelConfig> entities);

    /**
     * 修改数据
     *
     * @param buffLevelConfig 实例对象
     * @return 影响行数
     */
    int update(BuffLevelConfig buffLevelConfig);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}

