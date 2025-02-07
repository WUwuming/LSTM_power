package com.qiu.lstmpower.Util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;

public class JwtUtil {
    public String JwtToken(int UserId, String UserName, String UserEmail, String Password, String level) {
        Date date = new Date();
        int time = 1000 * 60 * 60; //1小时
        Date expireTime = new Date(date.getTime() + time);
        return JWT.create()
                .withExpiresAt(expireTime)//设置过期时间 1小时
                .withClaim("UserId", UserId) //用户ID
                .withClaim("UserName", UserName)    //用户名
                .withClaim("UserEmail", UserEmail)  //用户登入名
                .withClaim("Password", Password)       //用户密码
                .withClaim("level", level)    //用户权限
                .sign(Algorithm.HMAC256("PowerSystem"));    //签名
    }

    public String GetStringInfo(DecodedJWT verify,String key){
       return verify.getClaim(key).asString();
    }
}
