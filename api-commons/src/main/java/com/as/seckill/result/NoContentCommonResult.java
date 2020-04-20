package com.as.seckill.result;

public class NoContentCommonResult<T> extends CommonResult<T> {
    public NoContentCommonResult(){
        super(204, "要操作的数据已经不存在！");
    }
}
