package com.jeesite.modules.wms.config;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author yosaa
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@JsonInclude
public class Result<T> implements Serializable
{
    private static final long serialVersionUID = 1L;


    private int status;

    private int code;

    private String msg;

    private T data;

    public static <T> Result<T> ok() {
        return restResult(null, ResultConstants.SUCCESS, 1,null);
    }

    public static <T> Result<T> ok(T data) {
        return restResult(data, ResultConstants.SUCCESS, 1,null);
    }

    public static <T> Result<T> ok(T data, String msg) {
        return restResult(data, ResultConstants.SUCCESS, 1, msg);
    }

    public static <T> Result<T> ok(int status, String msg) {
        return restResult(null, ResultConstants.SUCCESS, status, msg);
    }

    public static <T> Result<T> ok(T data, int status, String msg) {
        return restResult(data, ResultConstants.SUCCESS, status, msg);
    }

    public static <T> Result<T> failed() {
        return restResult(null, ResultConstants.FAIL, -1, null);
    }

    public static <T> Result<T> failed(String msg) {
        return restResult(null, ResultConstants.FAIL, -1, msg);
    }

    public static <T> Result<T> failed(T data) {
        return restResult(data, ResultConstants.FAIL, -1, null);
    }

    public static <T> Result<T> failed(T data, String msg) {
        return restResult(data, ResultConstants.FAIL, -1, msg);
    }

    public static <T> Result<T> make(T data, int code, int status) {
        return restResult(data, code, status, null);
    }

    public static <T> Result<T> make(int code, String msg) {
        return restResult(null, code, -1, msg);
    }

    public static <T> Result<T> make(T data, int code, int status, String msg) {
        return restResult(data, code, status, msg);
    }

    private static <T> Result<T> restResult(T data, int code, int status,String msg) {
        Result<T> apiResult = new Result<>();
        apiResult.setCode(code);
        apiResult.setData(data);
        apiResult.setMsg(msg);
        return apiResult;
    }

}
