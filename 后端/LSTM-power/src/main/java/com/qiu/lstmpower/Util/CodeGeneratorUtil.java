package com.qiu.lstmpower.Util;


import cn.hutool.core.util.IdUtil;

import java.util.UUID;

/**
 * @description:数字生成器
 */
public class CodeGeneratorUtil {

    /**
     * 生成指定长度的验证码
     * @param length 验证码长度
     * @return
     */
    public static String generateCode(int length){
        return UUID.randomUUID().toString().substring(0,length);
    }

    /**
     * 雪花算法生成用户ID
     * @return
     */
    public static long snowflake(){
        return IdUtil.getSnowflakeNextId();
    }


}
