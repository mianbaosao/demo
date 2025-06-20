package org.example.suanfa.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author heweitao538 2025/6/20
 */
public class streamPractice {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // 练习1：筛选出所有偶数并收集到List
        List<Integer> res=numbers.stream()
                .filter(a->a%2==0)
                .collect(Collectors.toList());
        System.out.println(res);

        // 练习2：筛选出大于5的数字并打印
       numbers.stream()
                .filter(a->a>5)
                .forEach(a-> System.out.print(a+" "));


        List<String> words = Arrays.asList("Java", "Python", "C++", "JavaScript", "Go");

        // 练习3：将所有字符串转为大写
        List<String> upperCaseWords = words.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());

        // 练习4：计算每个单词的长度
        List<Integer> wordLengths = words.stream()
                .map(String::length)
                .collect(Collectors.toList());
        System.out.println(wordLengths);
    }
}
