package leetcode.duoweidp;

/**
 * @Description: 最长回文子串
 * @Author:bread
 * @Date: 2025-01-23 12:56
 */

/**
 * 主要是思考从中间向外拓展举个例子就是  bab   就是从a开始如果l==r就l-- r++ 需要主要的是需要判断基数和偶数两种情况 偶数: baab
 */
public class zuichang {
    public static String longestPalindrome(String s) {
        int res_len=0,res_start=0;
        for (int i = 0; i < s.length(); i++) {
            int l=i,r=i;
            while (l>=0&&r<s.length()&&s.charAt(l)==s.charAt(r)){
                if(r-l+1>res_len){
                    res_len=r-l+1;
                    res_start=l;
                }
                r++;
                l--;
            }
            //baab
            l=i;
            r=i+1;
            while (l>=0&&r<s.length()&&s.charAt(l)==s.charAt(r)){
                if(r-l+1>res_len){
                    res_len=r-l+1;
                    res_start=l;
                }
                r++;
                l--;
            }
        }
        return s.substring(res_start,res_len+res_start);
    }
    public static void main(String[] args) {
        String s="babad";
        System.out.println(longestPalindrome(s));
    }
}
