package leetcode.zhizheng;

/**
 * @Description: 接雨水
 * @Author:bread
 * @Date: 2025-02-25 15:44
 * 这个算法的基本思路是：对于每一个柱子，计算它上面能积多少水。水的高度是由它左边和右边的柱子的高度决定的
 */
public class yushui {
    public static int trap(int[] height) {
        int sum=0;
        for(int i=1;i<height.length-1;i++){
            int left=0;
            for(int j=i-1;j>=0;j--){
                left=Math.max(left,height[j]);
            }
            int right=0;
            for(int j1=i+1;j1<height.length;j1++){
                right=Math.max(height[j1],right);
            }
            int p=Math.min(left,right);
            if(p>height[i]){
                sum+=p-height[i];
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        int []height={4,2,0,3,2,5};
        System.out.println(trap(height));
    }
}
