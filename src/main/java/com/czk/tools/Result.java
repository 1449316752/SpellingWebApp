package com.czk.tools;

import java.io.Serializable;

public class Result  implements Serializable {
    private Integer code = null;
    private Object data = null;
    private String msg = null;

    public Result() {
    }

    public Result(Integer code, Object data) {
        this.data = data;
        this.code = code;
    }

    public Result(Integer code, Object data, String msg) {
        this.data = data;
        this.code = code;
        this.msg = msg;
    }

    public Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static Result Success(Object o){
        return new Result(Code.OK,o,"成功");
    }

    public static Result Success(Object o,String msg){
        return new Result(Code.OK,o,msg);
    }
    public static Result Success(){
        return new Result(Code.OK,null,"成功");
    }

    public static Result Error(String msg){
        return new Result(Code.ERR,msg);
    }

    public static Result Error(){
        return new Result(Code.ERR,"失败");
    }
}
