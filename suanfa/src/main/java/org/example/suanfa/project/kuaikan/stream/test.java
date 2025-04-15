package org.example.suanfa.project.kuaikan.stream;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class test {

    @Data
    public static class Conf {
        int level;
        int buffAddition; // 修正字段名首字母小写（Java命名规范）

        public Conf(int i, int i1) {
            this.level = i;
            this.buffAddition = i1;
        }
    }

    private static List<Conf> confList = new ArrayList<>();

    public static void main(String[] args) {
        // 初始化测试数据
        confList.add(new Conf(1, 10));  // level 1, buffAddition 10
        confList.add(new Conf(2, 20));
        confList.add(new Conf(3, 30));

        // 测试用例1：存在的level
        int lv = 1;
        testBuffAddition(lv); // 预期输出 10
        // 测试用例2：不存在的level
        lv = 3;
        testBuffAddition(lv);  // 预期输出 0
    }

    private static void testBuffAddition(int targetLevel) {
        BigDecimal buffRadio = confList.stream()
                .filter(conf -> conf.getLevel() == targetLevel)
                .findFirst()
                .map(conf -> new BigDecimal(conf.getBuffAddition()))
                .orElse(BigDecimal.ZERO);
        int maxLv = confList.stream().mapToInt(Conf::getLevel).max().orElse(0);
        System.out.println("=== 测试 ===");
        System.out.println("当前等级: " + targetLevel);
        System.out.println("找到的加成值: " + buffRadio);;
        System.out.println("找到最大等级为: " + maxLv);;
        System.out.println();
    }
}
