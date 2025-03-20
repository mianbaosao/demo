package org.example.suanfa.project.aop.res;

import java.io.Serializable;

public class RpcResult<T> implements Serializable {
    private static final long serialVersionUID = -8619791524285070745L;
    private T data;
    private int code;
    private String message;

    public static <T> RpcResult<T> result(ResponseCodeMsg code) {
        RpcResult<T> result = new RpcResult<T>();
        result.setCode(code.getCode());
        result.setMessage(code.getMessage());
        return result;
    }

    public static <T> RpcResult<T> result(T data, ResponseCodeMsg code) {
        RpcResult<T> result = new RpcResult<T>();
        result.setData(data);
        result.setCode(code.getCode());
        result.setMessage(code.getMessage());
        return result;
    }

    public static <T> RpcResult<T> result(int code, String message) {
        RpcResult<T> result = new RpcResult<T>();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    public static <T> RpcResult<T> result(T data, int code, String message) {
        RpcResult<T> result = new RpcResult<T>();
        result.setData(data);
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    public static <T> RpcResult<T> success() {
        RpcResult<T> result = new RpcResult<T>();
        result.setCode(ResponseCodeMsg.SUCCESS.getCode());
        result.setMessage(ResponseCodeMsg.SUCCESS.getMessage());
        return result;
    }

    public static <T> RpcResult<T> success(T data) {
        RpcResult<T> result = new RpcResult<T>();
        result.setData(data);
        result.setCode(ResponseCodeMsg.SUCCESS.getCode());
        result.setMessage(ResponseCodeMsg.SUCCESS.getMessage());
        return result;
    }

    public boolean isSuccess() {
        return this.code == ResponseCodeMsg.SUCCESS.getCode();
    }

    public RpcResult() {
    }

    public T getData() {
        return this.data;
    }

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof RpcResult)) {
            return false;
        } else {
            RpcResult<?> other = (RpcResult)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$data = this.getData();
                Object other$data = other.getData();
                if (this$data == null) {
                    if (other$data != null) {
                        return false;
                    }
                } else if (!this$data.equals(other$data)) {
                    return false;
                }

                if (this.getCode() != other.getCode()) {
                    return false;
                } else {
                    Object this$message = this.getMessage();
                    Object other$message = other.getMessage();
                    if (this$message == null) {
                        if (other$message != null) {
                            return false;
                        }
                    } else if (!this$message.equals(other$message)) {
                        return false;
                    }

                    return true;
                }
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof RpcResult;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $data = this.getData();
        result = result * 59 + ($data == null ? 43 : $data.hashCode());
        result = result * 59 + this.getCode();
        Object $message = this.getMessage();
        result = result * 59 + ($message == null ? 43 : $message.hashCode());
        return result;
    }

    public String toString() {
        return "RpcResult(data=" + this.getData() + ", code=" + this.getCode() + ", message=" + this.getMessage() + ")";
    }
}
