package org.example.suanfa.project.kuaikan.res;


import lombok.Data;

import java.io.Serializable;

/**
 * @author maxueyan at 2019-09-04
 */
@Data
public class BizResult<T>  implements Serializable {

    private static final long serialVersionUID = -8619791524285070745L;

    private T data;
    private int code;
    private String message;

    public static <T> BizResult<T> result(ResponseCodeMsg code) {
        BizResult<T> result = new BizResult<>();
        result.setCode(code.getCode());
        result.setMessage(code.getMessage());
        return result;
    }

    public static <T> BizResult<T> result(T data, ResponseCodeMsg code) {
        BizResult<T> result = new BizResult<>();
        result.setData(data);
        result.setCode(code.getCode());
        result.setMessage(code.getMessage());
        return result;
    }

    public static <T> BizResult<T> result(int code, String message) {
        BizResult<T> result = new BizResult<>();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    public static <T> BizResult<T> result(T data, int code, String message) {
        BizResult<T> result = new BizResult<>();
        result.setData(data);
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    public static <T> BizResult<T> success() {
        BizResult<T> result = new BizResult<>();
        result.setCode(ResponseCodeMsg.SUCCESS.getCode());
        result.setMessage(ResponseCodeMsg.SUCCESS.getMessage());
        return result;
    }

    public static <T> BizResult<T> success(T data) {
        BizResult<T> result = new BizResult<>();
        result.setData(data);
        result.setCode(ResponseCodeMsg.SUCCESS.getCode());
        result.setMessage(ResponseCodeMsg.SUCCESS.getMessage());
        return result;
    }

    public boolean isSuccess(){
        return this.code == ResponseCodeMsg.SUCCESS.getCode();
    }
}
