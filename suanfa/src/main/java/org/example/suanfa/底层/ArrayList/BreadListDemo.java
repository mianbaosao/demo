package org.example.suanfa.底层.ArrayList;

import java.util.ArrayList;
import java.util.List;

public class BreadListDemo {
    public static void main(String[] args) {
        // 1. 创建BreadList并添加元素
        BreadList<String> breadList = new BreadList<>();
        breadList.add("Bread");
        breadList.add("Butter");
        breadList.add("Jam");

        System.out.println("初始化列表: " + breadList);
        System.out.println("当前大小: " + breadList.size());

        // 2. 测试获取元素
        System.out.println("\n获取第二个元素: " + breadList.get(1));

        // 3. 测试删除元素
        String removed = breadList.remove(0);
        System.out.println("\n删除第一个元素(" + removed + ")后: " + breadList);
        System.out.println("删除后大小: " + breadList.size());

        // 4. 测试扩容
        System.out.println("\n--- 测试自动扩容 ---");
        BreadList<Integer> numbers = new BreadList<>(3); // 初始容量3
        for (int i = 1; i <= 10; i++) {
            numbers.add(i * 10);
            System.out.println("添加 " + (i * 10) + " → 当前容量: " + numbers.size() + 
                              "/" + getInternalArrayLength(numbers));
        }
        System.out.println("最终列表: " + numbers);
    }

    // 反射获取内部数组长度（仅用于演示扩容）
    private static int getInternalArrayLength(BreadList<?> list) {
        try {
            java.lang.reflect.Field field = BreadList.class.getDeclaredField("elements");
            field.setAccessible(true);
            return ((Object[]) field.get(list)).length;
        } catch (Exception e) {
            return -1;
        }
    }
}