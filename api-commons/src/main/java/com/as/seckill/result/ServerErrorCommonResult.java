package com.as.seckill.result;

public class ServerErrorCommonResult<T> extends CommonResult<T> {
    public ServerErrorCommonResult(){
        super(500, "服务器错误！o(╥﹏╥)o 请稍后再试");
    }
}
