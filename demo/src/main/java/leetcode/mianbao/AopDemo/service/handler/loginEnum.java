package leetcode.mianbao.AopDemo.service.handler;

/**
 * @Description: 登录枚举类
 * @Author:bread
 * @Date: 2024-11-06 20:15
 */
public enum loginEnum {
    WX(1,"微信登录"),
    QQ(2,"QQ登录"),
    ACOUNT(3,"账号登陆");

    public int code;
    public String desc;

    loginEnum(int i, String desc) {
        this.code=i;
        this.desc=desc;
    }

    public static loginEnum getByCode(int codeVal){
        for(loginEnum result :loginEnum.values()){
            if(result.code==codeVal){
                return result;
            }
        }
        return null;
    }
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
