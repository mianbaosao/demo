package org.example.suanfa.project.kuaikan.redis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class test {

    public static int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 1) + 1);
        }
        // Step 2: 将 map 转换为 List，然后按频率进行排序
        List<Map.Entry<Integer, Integer>> entryList = new ArrayList<>(map.entrySet());
        // 使用 Comparator 按 value 排序，按频率降序排序
        entryList.sort((entry1, entry2) -> entry2.getValue() - entry1.getValue());
        // Step 3: 提取前 k 个元素
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = entryList.get(i).getKey();
        }

        return result;

    }
}
