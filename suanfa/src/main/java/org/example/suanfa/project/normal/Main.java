package org.example.suanfa.project.normal;

import java.util.Scanner;

// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while (t != 0) {
            int n = in.nextInt();
            int []a = new int[n + 5];
            for (int i = 0; i < n; i++) {
                a[i] = in.nextInt();
            }
            System.out.println(find(a));
            t--;
        }
    }
    public static int find(int []a) {
        if(a.length<=1){
            return 0;
        }
        int min=Integer.MAX_VALUE;
        int sum=0;
        int []d= new int [a.length-1];
        for(int i=0;i<a.length-1;i++){
            d[i]=Math.abs(a[i]-a[i+1]);
            sum+=d[i];
        }
        min=sum;
        for(int l=0;l<a.length;l++){
            for(int r=l;r<a.length;r++){
                int cd=sum;
                if(l>0){
                    int od=Math.abs(a[l]-a[l-1]);
                    int nd=Math.abs((a[l]+1)-a[l-1]);
                    cd=cd-od+nd;
                }
               if(r<a.length-1){
                int od=Math.abs(a[r+1]-a[r]);
                int nd=Math.abs((a[r]+1)-a[r+1]);
                cd =cd -od+nd;
               }
               if(cd<min){
                min=cd;
               }
            }
        }
        return min;
    }
}