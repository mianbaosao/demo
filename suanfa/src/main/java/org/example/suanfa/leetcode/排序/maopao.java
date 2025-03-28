package org.example.suanfa.leetcode.排序;

import java.util.Arrays;

public class maopao {
    public static void main(String[] args) {
        int []a={1,2,3,4,5,9,8,7,6};
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = 0; j < a.length - 1 - i; j++) {
                // 如果前一个元素比后一个元素大，则交换它们
                if (a[j] > a[j + 1]) {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
        }
        Arrays.sort(a);
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i]+" ");
        }
    }
}
