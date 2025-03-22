package org.example.suanfa.leetcode.tanxin;

import java.util.ArrayList;

/**
 * 买卖股票的最佳时机
 */
public class gupiao {
    public  static int maxProfit(int[] prices) {
        int max=0,sell=0;
        for(int i=0;i<prices.length;i++){
            if(prices[i]<sell){
                sell=prices[i];
            }
            max=Math.max(max,prices[i]-sell);
        }
        return max;
    }

    public static void main(String[] args) {
        int []prices=new int[]{7,1,5,3,6,4};
        int res=maxProfit(prices);
        System.out.println(res);
    }
}
