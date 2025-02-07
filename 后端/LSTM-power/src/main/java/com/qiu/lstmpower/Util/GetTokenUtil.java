package com.qiu.lstmpower.Util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.qiu.lstmpower.Entity.User;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Date;
import java.util.List;

public class GetTokenUtil {
    public static User GetUserInfo(HttpServletRequest request) {
        User user = new User();
        Date date = new Date();
        Date expireTime = new Date(date.getTime());
        String token = request.getHeader("Authorization");
        if (token == null) {
            System.out.println("未得到token");
            return null;
        } else {
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("PowerSystem")).build();
            try {
                DecodedJWT verify = jwtVerifier.verify(token);
                String UserId = verify.getClaim("UserId").asString();
                String UserName = verify.getClaim("UserName").asString();
                String UserEmail = verify.getClaim("UserEmail").asString();
                String Password = verify.getClaim("Password").asString();
                String level = verify.getClaim("level").asString();
                long expireAt = verify.getExpiresAt().getTime();

                //判断
                if (UserId != null && UserName != null && UserEmail != null && Password != null && level != null && expireAt > expireTime.getTime()) {
                    user.setUserId(UserId);
                    user.setUserName(UserName);
                    user.setUserEmail(UserEmail);
                    user.setPassword(Password);
                    user.setLevel(level.charAt(0));
                }
            } catch (JWTVerificationException e) {
                System.out.println("验证失败");
                return null;
            }
        }
        return user;
    }

    public static String[] getRegisterInfo(String token){
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("PowerSystem")).build();
        DecodedJWT verify = jwtVerifier.verify(token);
        String Username = verify.getClaim("UserName").asString();
        String UserEmail = verify.getClaim("UserEmail").asString();
        String Password = verify.getClaim("Password").asString();

        String[] value = {Username,UserEmail,Password};
        return value;

    }
}
