package org.example.suanfa.leetcode.排序;

import java.util.Arrays;

/**
 *  快速排序（挖坑法递归）
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] arr = new int[]{1,3,2,5,4};
        sort(arr,0,arr.length-1);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+" ");
        }
    }
    public static void sort(int arr[], int low, int high) {
        if (arr == null || arr.length <= 0) {
            return;
        }
        if (low >= high) {
            return;
        }
        int left = low;
        int right = high;
        int temp = arr[left]; //挖坑1：保存基准的值

        while (left < right) {
            while (left < right && arr[right] >= temp) {
                right--;
            }
            arr[left] = arr[right]; //坑2：从后向前找到比基准小的元素，插入到基准位置坑1中
            while (left < right && arr[left] <= temp) {
                left++;
            }
            arr[right] = arr[left]; //坑3：从前往后找到比基准大的元素，放到刚才挖的坑2中
        }
        arr[left] = temp; //基准值填补到坑3中，准备分治递归快排
        System.out.println("Sorting: " + Arrays.toString(arr));
        sort(arr, low, left - 1);
        sort(arr, left + 1, high);
    }
}
