package com.qiu.lstmpower.Controller;

import com.qiu.lstmpower.Entity.User;
import com.qiu.lstmpower.Service.UserService;
import com.qiu.lstmpower.Util.CodeGeneratorUtil;
import com.qiu.lstmpower.Util.JsonResult;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/User")
public class UserController {
    @Resource
    UserService userService;

    @PostMapping("/Register")
    @ResponseBody
    public JsonResult Register(String UserName, String UserEmail, String Password,String Code) {
        String UserId = CodeGeneratorUtil.snowflake();  //获取用户ID
        User user = new User();
        user.setUserId(UserId);
        user.setUserName(UserName);
        user.setUserEmail(UserEmail);
        user.setPassword(Password);
        user.setLevel('0');
        return userService.Register(user,Code);
    }

    @GetMapping("/Login")
    @ResponseBody
    public JsonResult Login(String UserEmail,String Password){
        JsonResult jsonResult;
        jsonResult = userService.Login(UserEmail,Password);
        return jsonResult;
    }
}
