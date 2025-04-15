package org.example.suanfa.shiyan.controller;

import javax.annotation.Resource;

import org.example.suanfa.shiyan.dao.UserDao;
import org.example.suanfa.shiyan.entity.User;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserDaoTest {

    @Resource
    private UserDao userDao;

    // 测试一对一：嵌套查询
    @Test
    public void testQueryWithAddressByNestedQuery() {
        User user = userDao.queryWithAddressByNestedQuery(1);
        System.out.println("嵌套查询 - 用户信息: " + user);
        System.out.println("地址: " + user.getAddress());
    }

    // 测试一对一：嵌套结果
    @Test
    public void testQueryWithAddressByNestedResult() {
        User user = userDao.queryWithAddressByNestedResult(1);
        System.out.println("嵌套结果 - 用户信息: " + user);
        System.out.println("地址: " + user.getAddress());
    }

    // 测试一对多：嵌套查询
    @Test
    public void testQueryWithRolesByNestedQuery() {
        User user = userDao.queryWithRolesByNestedQuery(1);
        System.out.println("嵌套查询 - 用户信息: " + user);
        System.out.println("角色列表: " + user.getRoles());
    }

    // 测试一对多：嵌套结果
    @Test
    public void testQueryWithRolesByNestedResult() {
        User user = userDao.queryWithRolesByNestedResult(1);
        System.out.println("嵌套结果 - 用户信息: " + user);
        System.out.println("角色列表: " + user.getRoles());
    }
}