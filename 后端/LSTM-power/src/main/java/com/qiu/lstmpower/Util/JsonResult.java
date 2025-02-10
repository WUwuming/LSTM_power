package com.qiu.lstmpower.Util;

import lombok.Data;

import java.io.Serializable;

@Data
public class JsonResult implements Serializable {
    private int code;
    private String msg;
    private Object data;

    public JsonResult setData(Object data) {
        this.data = data;
        return this;
    }

    /**
     * 自定义文本
     *
     * @param code 代码
     * @param msg  文本
     * @return
     */
    public JsonResult Custom(int code, String msg) {
        JsonResult jr = new JsonResult();
        jr.code = code;
        jr.msg = msg;
        return jr;
    }

    /**
     * 操作成功
     */
    public static JsonResult OK() {
        JsonResult jr = new JsonResult();
        jr.code = 200;
        jr.msg = "HTTP OK";
        return jr;
    }

    public static JsonResult Fail(){
        JsonResult jr = new JsonResult();
        jr.code = 400;
        jr.msg = "Fail";
        return jr;
    }

    public static JsonResult loginFail() {
        JsonResult jr = new JsonResult();
        jr.code = 201;
        jr.msg = "账号或密码错误";
        return jr;
    }

    public static JsonResult RegisterFail(){
        JsonResult jr = new JsonResult();
        jr.code = 202;
        jr.msg = "注册失败，已存在该用户";
        return jr;
    }

    public static JsonResult CodeFail(){
        JsonResult jr = new JsonResult();
        jr.code = 203;
        jr.msg = "验证码错误";
        return jr;
    }

    public static JsonResult OutOfToken(){
        JsonResult jr = new JsonResult();
        jr.code = 210;
        jr.msg = "Token错误";
        return jr;
    }
}
