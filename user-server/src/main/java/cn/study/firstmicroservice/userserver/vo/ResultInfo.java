package cn.study.firstmicroservice.userserver.vo;

import java.io.Serializable;

public class ResultInfo<T> implements Serializable{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String code;

    private String msg;

    private T data;

    public ResultInfo() {
    }

    @Override
    public String toString() {
        return "ResultInfo{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", code=" + data +
                '}';
    }

    public ResultInfo(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data
    ) {
        this.data = data;
    }

}

