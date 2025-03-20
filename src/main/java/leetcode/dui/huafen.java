package leetcode.dui;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 划分字母区间
 * @Author:bread
 * @Date: 2025-01-18 11:11
 * 这个主要是要想到第一步用数组表示每个字母最后出现的位置，然后根据位置进行判断
 */
public class huafen {
    public static List<Integer> partitionLabels(String s) {
        int []hash=new int[30];
        char[] s1 = s.toCharArray();
        for(int i=0;i<s.length();i++){
            hash[s1[i]-'a']=i;
        }
        int l=0,r=0,p;
        List <Integer>res=new ArrayList<>();
        for(int i=0;i<s.length();i++){
            r=Math.max(r,hash[s1[i]-'a']);
            if(r==i){
                p=(r-l+1);
                l=i+1;
                res.add(p);
            }
        }
        return res;
    }
    public static void main(String[] args) {
        String s="eccbbbbdec";
        System.out.println(partitionLabels(s));
    }
}
