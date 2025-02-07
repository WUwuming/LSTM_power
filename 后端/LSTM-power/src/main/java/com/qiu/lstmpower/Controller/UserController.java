package com.qiu.lstmpower.Controller;

import com.qiu.lstmpower.Entity.User;
import com.qiu.lstmpower.Service.UserService;
import com.qiu.lstmpower.Util.CodeGeneratorUtil;
import com.qiu.lstmpower.Util.JsonResult;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/User")
public class UserController {
    @Resource
    UserService userService;

    @RequestMapping(value = "/Register",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult Register(String UserName, String UserEmail, String Password,String Code) {
        String UserId = CodeGeneratorUtil.snowflake();  //获取用户ID
        System.out.println(UserEmail+':'+Password);
        User user = new User();
        user.setUserId(UserId);
        user.setUserName(UserName);
        user.setUserEmail(UserEmail);
        user.setPassword(Password);
        user.setLevel('0');
        return userService.Register(user,Code);
    }
}
