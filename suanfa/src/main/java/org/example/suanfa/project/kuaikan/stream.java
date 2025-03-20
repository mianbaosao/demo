package org.example.suanfa.project.kuaikan;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class stream {
    public static void main(String[] args) {
        List<List<Integer>>res = new ArrayList<>();
        List<Integer>test = new ArrayList<>();
        test.add(0);
        test.add(1);
        test.add(3);
        test.add(5);
        test.add(9);
        List<Integer> list = test.stream().filter(i -> i>3).collect(Collectors.toList());
        System.out.println(list);
        int x=123;
        System.out.println(x);
    }
}
