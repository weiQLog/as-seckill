package com.as.seckill.result;

/**
 * 版本不一致的错误
 */
public class VersionWrongCommonResult<T> extends CommonResult<T>  {
    public VersionWrongCommonResult(){
        super(203, "在此操作前该数据已被修改，请刷新重试");
    }
}
