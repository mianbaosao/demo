package org.example.suanfa.leetcode.shuzu;

import java.util.Arrays;

/**
 * @author heweitao538 2025/7/18
 */
public class shuzuTest {
    public static void main(String[] args) {
        int []a={2,1,0,9,8,5,6,7,4,3};
        Arrays.sort(a);
        quickSort(a,0,a.length-1);
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i]+" ");
        }
    }

    private static void quickSort(int[] a, int low, int high) {
        if(low>high){
            return;
        }
        int l=low,r=high;
        int temp=a[l];
        while (l<r) {
            while (l < r && a[r] > temp) {
                r--;
            }
            a[l] = a[r];
            while (l < r && a[l] < temp) {
                l++;
            }
            a[r] = a[l];
            quickSort(a, low, r - 1);
            quickSort(a, l + 1, high);
        }
    }
}
