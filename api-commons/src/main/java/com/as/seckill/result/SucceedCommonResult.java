package com.as.seckill.result;

public class SucceedCommonResult<T> extends CommonResult<T> {
    public SucceedCommonResult(){
        super(200, "成功！");
    }
    public SucceedCommonResult(T data){
        super(200, "成功！", data);
    }
}
