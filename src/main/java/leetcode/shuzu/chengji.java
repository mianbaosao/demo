package leetcode.shuzu;

/**
 * @Description: 除自身以外数组的乘积
 * @Author:bread
 * @Date: 2024-12-03 20:44
 */
public class chengji {
    /**
     * 暴力时间超限
     * @param nums
     * @return
     */
  /*  public static int[] productExceptSelf(int[] nums) {
        int[] res = new int[nums.length];
        int s = 1;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                if(j==i){
                    continue;
                }else{
                    s = s*nums[j];
                }
            }
            res[i] = s;
            s=1;
        }
        return res;
    }*/
   /* public static int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        res[0] = 1;
        for (int i = 1; i < n; i++) {
            res[i] = res[i - 1] * nums[i - 1];
        }
        // 临时变量用来保存右侧所有元素的乘积
        int right = 1;
        for (int i = n - 1; i >= 0; i--) {
            res[i] *= right;  // 乘以右侧的乘积
            right *= nums[i]; // 更新右侧乘积
        }

        return res;
    }*/
    public static int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        int[] r = new int[n];
        res[0] = 1;
        for (int i = 1; i < n; i++) {
            res[i] = res[i - 1] * nums[i - 1];
        }
        // 临时变量用来保存右侧所有元素的乘积
        r[n-1]=1;
        for (int i = n - 1; i >= 0; i--) {
            if(i!=n-1) {
                r[i] = r[i + 1] * nums[i + 1];
            }
            res[i] *= r[i];  // 乘以右侧的乘积
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3,4};
        int[] ints = productExceptSelf(nums);
        for (int i = 0; i < ints.length; i++) {
            System.out.print(ints[i]+" ");
        }
    }
}
