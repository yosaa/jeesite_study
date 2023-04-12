package com.jeesite.modules.wms.util;


import com.jeesite.modules.wms.config.Result;

/**
 * @Author christina
 * @Date 2022/12/15 17:57
 */
public class ResultUtil {

    public static Result getResult(boolean success, String msg) {
        if(success){
            return Result.ok(msg + "成功");
        }
        return Result.failed(msg + "失败");
    }

    public static Result getResult(int rows, String msg) {
        if(rows > 0){
            return Result.ok(msg + "成功");
        }
        return Result.failed(msg + "失败");
    }
}
