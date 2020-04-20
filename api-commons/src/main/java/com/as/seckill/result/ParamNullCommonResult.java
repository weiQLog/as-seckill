package com.as.seckill.result;

import com.as.seckill.result.CommonResult;

public class ParamNullCommonResult<T> extends CommonResult<T> {
    public ParamNullCommonResult(){
        super(403, "参数不能为空，拒绝执行");
    }
}
