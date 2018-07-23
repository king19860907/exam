package com.majun.exam.base.dto;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * Created by jun_ma on 2016/4/14.
 */
public class InfoDto<T> implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 8230333740798084340L;

    private String msg;

    private Integer flag;

    private T result;

    public final static Integer SUCCESS = 1;

    public final static Integer ERROR = 0;

    public final static Integer NOT_LOGIN = -1;

    public static <String>InfoDto notLogin(){
        return defaultError(NOT_LOGIN,"未登录");
    }

    public static <T>InfoDto defaultSuccess(T result){
        InfoDto info = null;
        info = new InfoDto(InfoDto.SUCCESS, "success",result);
        return info;
    }

    public static<T>InfoDto defaultError(){
        return defaultError("System Error");
    }

    public static<T>InfoDto defaultError(String errorMsg){
        return defaultError(InfoDto.ERROR,errorMsg);
    }

    public static<T>InfoDto defaultError(Integer errorCode,String errorMsg){
        InfoDto info = null;
        info = new InfoDto(errorCode, errorMsg);
        return info;
    }

    public static<T>InfoDto error(String message){
        return new InfoDto(InfoDto.ERROR,message);
    }
    
    public InfoDto(Integer flag, String msg, T result) {
        this.flag = flag;
        this.msg = msg;
        this.result = result;
    }

    public InfoDto(Integer flag, String msg) {
        this.flag = flag;
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public boolean success(){
        return this.flag.intValue() == InfoDto.SUCCESS.intValue();
    }

    @Override
    public String toString() {
        return "InfoDto{" +
                "result=" + result +
                ", msg='" + msg + '\'' +
                ", flag=" + flag +
                '}';
    }
}
