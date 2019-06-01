package com.example.demo.model.overview;

/**
 * @program: demo
 * @description: 返回数据格式
 * @author: tyq
 * @create: 2019-04-25 13:58
 **/
public class Result<Type> {
    private ResultCode status;
    private String message;
    private Type data;

    public ResultCode getStatus() {
        return status;
    }

    public void setStatus(ResultCode status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Type getData() {
        return data;
    }

    public void setData(Type data) {
        this.data = data;
    }
}
