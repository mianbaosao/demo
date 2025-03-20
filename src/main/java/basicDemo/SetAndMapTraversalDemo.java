package basicDemo;

import java.util.*;

public class SetAndMapTraversalDemo {

    public static void main(String[] args) {
        // 示例1：遍历 Set 集合
        Set<String> set = new HashSet<>();
        set.add("Apple");
        set.add("Banana");
        set.add("Cherry");

        System.out.println("遍历 Set 集合：");
        // 方法1：使用增强 for 循环
        System.out.println("方法1：增强 for 循环");
        for (String fruit : set) {
            System.out.println(fruit);
        }

        // 方法2：使用 Iterator 迭代器
        System.out.println("方法2：Iterator 迭代器");
        Iterator<String> setIterator = set.iterator();
        while (setIterator.hasNext()) {
            System.out.println(setIterator.next());
        }

        // 方法3：使用 forEach + Lambda 表达式
        System.out.println("方法3：forEach + Lambda 表达式");
        set.forEach(fruit -> System.out.println(fruit));

        // 示例2：遍历 Map 集合
        Map<String, Integer> map = new HashMap<>();
        map.put("Apple", 10);
        map.put("Banana", 20);
        map.put("Cherry", 30);

        System.out.println("\n遍历 Map 集合：");
        // 方法1：遍历 EntrySet（键值对）
        System.out.println("方法1：遍历 EntrySet");
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }

        // 方法2：遍历 KeySet（键）
        System.out.println("方法2：遍历 KeySet");
        for (String key : map.keySet()) {
            System.out.println("Key: " + key + ", Value: " + map.get(key));
        }

        // 方法3：遍历 Values（值）
        System.out.println("方法3：遍历 Values");
        for (Integer value : map.values()) {
            System.out.println("Value: " + value);
        }

        // 方法4：使用 Iterator 遍历 EntrySet
        System.out.println("方法4：Iterator 遍历 EntrySet");
        Iterator<Map.Entry<String, Integer>> mapIterator = map.entrySet().iterator();
        while (mapIterator.hasNext()) {
            Map.Entry<String, Integer> entry = mapIterator.next();
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }

        // 方法5：使用 forEach + Lambda 表达式
        System.out.println("方法5：forEach + Lambda 表达式");
        map.forEach((key, value) -> System.out.println("Key: " + key + ", Value: " + value));
    }
}