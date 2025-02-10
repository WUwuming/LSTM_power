package com.qiu.lstmpower.Service;

import com.qiu.lstmpower.Entity.User;
import com.qiu.lstmpower.Util.JsonResult;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    JsonResult Register(User user,String Code);
    JsonResult Login(String UserEmail,String Password);
}
