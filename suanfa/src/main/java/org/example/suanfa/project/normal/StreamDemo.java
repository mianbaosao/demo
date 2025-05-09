package org.example.suanfa.project.normal;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class StreamDemo {

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 5, 6, 7, 8, 9, 10);
        List<String> words = Arrays.asList("apple", "banana", "apricot", "kiwi", "orange");

        System.out.println("--- 过滤 (filter) ---");
        numbers.stream()
                .filter(n -> n % 2 == 0) // 过滤偶数
                .forEach(System.out::println);

        System.out.println("\n--- 映射 (map) ---");
        words.stream()
                .map(String::toUpperCase) // 将字符串转换为大写
                .forEach(System.out::println);

        System.out.println("\n--- 去重 (distinct) ---");
        numbers.stream()
                .distinct() // 移除重复元素
                .forEach(System.out::println);

        System.out.println("\n--- 排序 (sorted) ---");
        words.stream()
                .sorted() // 默认自然排序
                .forEach(System.out::println);

        System.out.println("\n--- 限制 (limit) ---");
        numbers.stream()
                .limit(5) // 取前 5 个元素
                .forEach(System.out::println);

        System.out.println("\n--- 跳过 (skip) ---");
        numbers.stream()
                .skip(3) // 跳过前 3 个元素
                .forEach(System.out::println);

        System.out.println("\n--- 归约 (reduce) ---");
        int sum = numbers.stream()
                .reduce(0, Integer::sum); // 求和，初始值为 0
        System.out.println("Sum: " + sum);

        System.out.println("\n--- 收集到 List (collect to List) ---");
        List<Integer> evenNumbers = numbers.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());
        System.out.println(evenNumbers);

        System.out.println("\n--- 收集到 Set (collect to Set) ---");
        Set<Integer> uniqueNumbers = numbers.stream()
                .collect(Collectors.toSet());
        System.out.println(uniqueNumbers);

        System.out.println("\n--- 收集到 Map (collect to Map) ---");
        Map<String, Integer> wordLengths = words.stream()
                .collect(Collectors.toMap(s -> s, String::length)); // key 是单词，value 是长度
        System.out.println(wordLengths);

        System.out.println("\n--- 查找第一个 (findFirst) ---");
        words.stream()
                .filter(s -> s.startsWith("a"))
                .findFirst()
                .ifPresent(System.out::println); // 如果存在则打印

        System.out.println("\n--- 查找任意一个 (findAny) ---");
        numbers.stream()
                .filter(n -> n > 5)
                .findAny()
                .ifPresent(System.out::println); // 如果存在则打印

        System.out.println("\n--- 是否所有元素匹配 (allMatch) ---");
        boolean allGreaterThanZero = numbers.stream()
                .allMatch(n -> n > 0);
        System.out.println("All greater than zero: " + allGreaterThanZero);

        System.out.println("\n--- 是否有任意元素匹配 (anyMatch) ---");
        boolean hasEvenNumber = numbers.stream()
                .anyMatch(n -> n % 2 == 0);
        System.out.println("Has even number: " + hasEvenNumber);

        System.out.println("\n--- 是否没有元素匹配 (noneMatch) ---");
        boolean noneNegative = numbers.stream()
                .noneMatch(n -> n < 0);
        System.out.println("None negative: " + noneNegative);

        System.out.println("\n--- 计数 (count) ---");
        long countGreaterThanFive = numbers.stream()
                .filter(n -> n > 5)
                .count();
        System.out.println("Count greater than five: " + countGreaterThanFive);
    }
}