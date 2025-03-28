package org.example.suanfa.leetcode.排序;

public class MergeSort {

    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        int[] temp = new int[arr.length]; // 临时数组用于合并
        mergeSort(arr, 0, arr.length - 1, temp);
    }

    private static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left >= right) {
            return; // 递归终止条件
        }
        int mid = left + (right - left) / 2;
        mergeSort(arr, left, mid, temp);      // 递归排序左半部分
        mergeSort(arr, mid + 1, right, temp);  // 递归排序右半部分
        merge(arr, left, mid, right, temp);    // 合并两个有序子数组
    }

    private static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int i = left;    // 左子数组起始索引
        int j = mid + 1; // 右子数组起始索引
        int k = 0;       // 临时数组索引

        // 按顺序合并左右子数组
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }

        // 处理剩余元素（左子数组或右子数组可能未完全合并）
        while (i <= mid) {
            temp[k++] = arr[i++];
        }
        while (j <= right) {
            temp[k++] = arr[j++];
        }

        // 将合并后的数据拷贝回原数组
        System.arraycopy(temp, 0, arr, left, k);
    }

    public static void main(String[] args) {
        int[] arr = {12, 11, 13, 5, 6, 7};
        mergeSort(arr);
        System.out.println("排序结果:");
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
}