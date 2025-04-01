package org.example.suanfa.底层.Hashmap;

/**
 * 扰动函数测试【高低位或与】
 */
public class HashTest {
    // 无扰动函数
    static int hashNoDisturb(int h) {
        return h;
    }
    
    // 有扰动函数 (JDK8 HashMap实现)
    static int hashWithDisturb(int h) {
        return h ^ (h >>> 16);
    }
    
    // 打印哈希分析结果
    static void printHashAnalysis(String str) {
        int hashCode = str.hashCode();
        int noDisturb = hashNoDisturb(hashCode);
        int withDisturb = hashWithDisturb(hashCode);
        int bucketNoDisturb = noDisturb & 15;  // 模拟16个桶
        int bucketWithDisturb = withDisturb & 15;
        
        System.out.printf("%-10s | 原始哈希: %11d (%32s) | 无扰动: 桶%d | 有扰动: 桶%d%n",
                str,
                hashCode,
                Integer.toBinaryString(hashCode),
                bucketNoDisturb,
                bucketWithDisturb);
    }
    
    public static void main(String[] args) {
        System.out.println("字符串      | 原始哈希值 (二进制表示)                     | 无扰动桶位 | 有扰动桶位");
        System.out.println("-------------------------------------------------------------------------------");
        
        printHashAnalysis("Aa");
        printHashAnalysis("BB");
        printHashAnalysis("Aa1");
        printHashAnalysis("BB2");
        printHashAnalysis("hello");
        printHashAnalysis("world");
        printHashAnalysis("hash");
        printHashAnalysis("code");
        printHashAnalysis("java");
        printHashAnalysis("扰动");
        printHashAnalysis("函数");
        printHashAnalysis("冲突");
        printHashAnalysis("测试");
    }
}