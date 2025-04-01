package org.example.suanfa.底层.Hashmap;

public class Main {
    public static void main(String[] args) {
        SimpleHashMap<String, Integer> map = new SimpleHashMap<>();
        
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);
        
        System.out.println(map.get("two")); // 输出: 2
        System.out.println(map.size());     // 输出: 3
        
        map.remove("two");
        System.out.println(map.get("two")); // 输出: null
    }
}