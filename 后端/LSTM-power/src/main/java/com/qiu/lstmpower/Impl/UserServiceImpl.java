package com.qiu.lstmpower.Impl;

import com.qiu.lstmpower.Dao.UserDao;
import com.qiu.lstmpower.Entity.User;
import com.qiu.lstmpower.Service.UserService;
import com.qiu.lstmpower.Util.JsonResult;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;
    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public JsonResult Register(User user, String Code) {
        JsonResult jsonResult;
        //验证码判断
        if (CheckVerificationCode(user.getUserEmail(), Code)) {
            if (userDao.Existence(user.getUserEmail()).isEmpty()) {
                userDao.Register(user);
                jsonResult = JsonResult.OK();
            } else {
                jsonResult = JsonResult.RegisterFail();
            }
        } else jsonResult = JsonResult.CodeFail();

        return jsonResult;
    }

    @Override
    public JsonResult Login(String UserEmail, String Password) {
        JsonResult jsonResult;

        User user = userDao.Login(UserEmail,Password);

        if (user == null){
            jsonResult = JsonResult.loginFail();
        }else {
            jsonResult = JsonResult.OK();
            user.setPassword("");
            jsonResult.setData(user);   //返回用户ID
        }
        return jsonResult;
    }


    private Boolean CheckVerificationCode(String userEmail, String Code) {
        String storedCode = redisTemplate.opsForValue().get(userEmail);
        if(!Code.equals("") && storedCode.equals(Code)){
            redisTemplate.delete(userEmail); //验证成功后删除该键值对
            return true;
        }else return false;
    }
}
