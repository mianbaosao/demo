package org.example.suanfa.project.normal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;

public class CollectionTraversalExample {

    public static void main(String[] args) {
        // HashMap 遍历
        HashMap<String, Integer> map = new HashMap<>();
        map.put("apple", 1);
        map.put("banana", 2);
        map.put("cherry", 3);

        System.out.println("HashMap 遍历:");
        // 遍历键值对 (EntrySet)
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }
        // 使用 Lambda 表达式
        map.forEach((key, value) -> System.out.println("Key: " + key + ", Value: " + value));
        System.out.println();

        // ArrayList 遍历
        ArrayList<String> list = new ArrayList<>();
        list.add("one");
        list.add("two");
        list.add("three");

        System.out.println("ArrayList 遍历:");
        // foreach 循环
        for (String item : list) {
            System.out.println(item);
        }
        // 使用 Lambda 表达式
        list.forEach(System.out::println);
        System.out.println();

        // HashSet 遍历
        HashSet<String> set = new HashSet<>();
        set.add("red");
        set.add("green");
        set.add("blue");

        System.out.println("HashSet 遍历:");
        // foreach 循环
        for (String item : set) {
            System.out.println(item);
        }
        // 使用 Lambda 表达式
        set.forEach(System.out::println);
        System.out.println();

        // LinkedList 遍历
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("first");
        linkedList.add("second");
        linkedList.add("third");

        System.out.println("LinkedList 遍历:");
        // foreach 循环
        for (String item : linkedList) {
            System.out.println(item);
        }
        // 使用 Lambda 表达式
        linkedList.forEach(System.out::println);
    }
}