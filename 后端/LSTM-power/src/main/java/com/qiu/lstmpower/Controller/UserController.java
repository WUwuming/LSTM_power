package com.qiu.lstmpower.Controller;

import com.qiu.lstmpower.Entity.User;
import com.qiu.lstmpower.Service.UserService;
import com.qiu.lstmpower.Util.CodeGeneratorUtil;
import com.qiu.lstmpower.Util.JsonResult;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/User")
public class UserController {
    @Resource
    UserService userService;

    /**
     * @param UserName  用户名
     * @param UserEmail 用户邮箱
     * @param Password  用户密码
     * @param Code      用户输入的邮箱验证码
     * @return
     */

    @PostMapping("/Register")
    @ResponseBody
    public JsonResult Register(String UserName, String UserEmail, String Password, String Code) {
        String UserId = CodeGeneratorUtil.snowflake();  //获取用户ID
        User user = new User();
        user.setUserId(UserId);
        user.setUserName(UserName);
        user.setUserEmail(UserEmail);
        user.setPassword(Password);
        user.setLevel('0');
        return userService.Register(user, Code);
    }

    /**
     * @param response
     * @param UserEmail 用户登入邮箱
     * @param Password  用户密码
     * @return
     */
    @GetMapping("/Login")
    @ResponseBody
    public JsonResult Login(HttpServletRequest request, HttpServletResponse response, String UserEmail, String Password) {
        JsonResult jsonResult;
        jsonResult = userService.Login(UserEmail, Password);
        if (jsonResult.getData() != null) {
            HttpSession session = request.getSession(true);
            session.setMaxInactiveInterval(60 * 60 * 24 * 3);
            session.setAttribute("user", jsonResult.getData());
        }
        return jsonResult;
    }

    @GetMapping("/CheckSession")
    @ResponseBody
    public Boolean CheckSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        //session不存在
        if (session == null) return false;
            //session存在
        else {
            User user = (User) session.getAttribute("user");
            //session无效
            return user != null;  //session有效
        }
    }
}
