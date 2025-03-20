package leetcode.huisu;

import javax.swing.plaf.BorderUIResource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 括号生成
 * @Author:bread
 * @Date: 2025-01-13 10:48
 * 记住是def然后就很简单，记得参数为左括号数和右括号数字
 */
public class kuohaoshengcheng {
    public  static List<String> res= null;
    public static List<String> generateParenthesis(int n) {
        res = new ArrayList<>();
        dfs(0,0,n,"",res);
        return res;
    }
    public static void dfs(int lc,int rc,int n,String str,List<String> res){
        if(lc==n&&rc==n){
            res.add(str);
        }
        if(lc<n){
            dfs(lc+1,rc,n,str+"(",res);
        }
        if(rc<lc){
            dfs(lc,rc+1,n,str+")",res);
        }
    }
    public static void main(String[] args) {
        generateParenthesis(3);
        System.out.println(res);
    }

}
