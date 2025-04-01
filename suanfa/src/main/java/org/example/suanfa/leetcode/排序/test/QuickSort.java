package org.example.suanfa.leetcode.排序.test;

public class QuickSort {

    public static void main(String[] args) {
        int[] arr = {1, 3, 2, 8, 4, 7};
        quickSort(arr, 0, arr.length - 1);
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
    private static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pivot = arr[low];    // 选第一个元素作为基准
            int left = low + 1;      // 左指针从第二个元素开始
            int right = high;        // 右指针从末尾开始

            while (left <= right) {
                // 左指针找比 pivot 大的
                while (left <= right && arr[left] <= pivot) {
                    left++;
                }
                // 右指针找比 pivot 小的
                while (left <= right && arr[right] >= pivot) {
                    right--;
                }
                // 如果没交叉，交换
                if (left < right) {
                    int temp = arr[left];
                    arr[left] = arr[right];
                    arr[right] = temp;
                }
            }
            // 把 pivot 放到正确位置（right）
            int temp = arr[low];
            arr[low] = arr[right];
            arr[right] = temp;

            // 递归排序左右两部分
            quickSort(arr, low, right - 1);
            quickSort(arr, right + 1, high);
        }
    }
}