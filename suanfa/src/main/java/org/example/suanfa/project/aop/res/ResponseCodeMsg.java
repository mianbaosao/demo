package org.example.suanfa.project.aop.res;

import java.io.Serializable;

/** 各业务自己的错误码继承这个类
 * Created by yushixiang on 08/09/2017.
 */
public class ResponseCodeMsg implements Serializable {

    public static final ResponseCodeMsg SYSTEM_ERROR = new ResponseCodeMsg(2005, "服务器暂时不可用,请稍后重试");
    public static final ResponseCodeMsg APP_UPGRADE = new ResponseCodeMsg(199, "请升级APP");
    public static final ResponseCodeMsg SUCCESS = new ResponseCodeMsg(200, "ok");
    public static final ResponseCodeMsg BAD_REQUEST = new ResponseCodeMsg(400, "bad request");
    public static final ResponseCodeMsg NEED_LOGIN = new ResponseCodeMsg(401, "用户未登陆");
    public static final ResponseCodeMsg NEED_LOGIN_TOAST = new ResponseCodeMsg(402, "已在其他设备登录");
    @Deprecated
    public static final ResponseCodeMsg INVALID_PARAM = new ResponseCodeMsg(403, "参数非法");
    public static final ResponseCodeMsg FORBIDDEN = new ResponseCodeMsg(403, "Forbidden");
    public static final ResponseCodeMsg NOT_FOUND = new ResponseCodeMsg(404, "not found");

    /**
     * 方法限流状态
     */
    public static final ResponseCodeMsg FLOW_LIMIT = new ResponseCodeMsg(521, "限流");

    /**
     * 方法降级状态
     */
    public static final ResponseCodeMsg DEGRADE = new ResponseCodeMsg(522, "降级");

    private static final long serialVersionUID = 3428541144983776860L;

    private int code;
    private String message;

    protected ResponseCodeMsg(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getMsgWithTemplate(Object... value) {
        return String.format(message, value);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ResponseCodeMsg{")
                .append("code=").append(code)
                .append(", message=").append(message)
                .append("}");
        return sb.toString();
    }

}