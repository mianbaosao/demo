package org.example.suanfa.project.normal;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

/**
 * 布隆过滤器
 * redis用redisson和jedis都可以
 */
public class BloomFilterExample {
    public static void main(String[] args) {
        // 创建布隆过滤器，预计插入100个元素，误判率为0.01
        BloomFilter<String> bloomFilter = BloomFilter.create(
                Funnels.stringFunnel(java.nio.charset.StandardCharsets.UTF_8),
                100,
                0.01
        );

        // 插入元素
        bloomFilter.put("bread");
        bloomFilter.put("666");
        bloomFilter.put("八股文");

        // 判断元素是否存在
        System.out.println(bloomFilter.mightContain("bread")); // true
        System.out.println(bloomFilter.mightContain("milk"));  // false

        // 测试误判率
        int falsePositives = 0;
        for (int i = 0; i < 1000; i++) {
            if (bloomFilter.mightContain("未添加元素" + i)) {
                falsePositives++;
            }
        }
        System.out.println("误判数量: " + falsePositives);
    }
}