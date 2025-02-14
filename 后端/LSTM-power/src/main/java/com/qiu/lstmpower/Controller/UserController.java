package com.qiu.lstmpower.Controller;

import com.qiu.lstmpower.Entity.User;
import com.qiu.lstmpower.Service.UserService;
import com.qiu.lstmpower.Util.CodeGeneratorUtil;
import com.qiu.lstmpower.Util.JsonResult;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
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
        if (UserName == null && UserEmail == null && Password == null && Code == null)
            return JsonResult.Fail();
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
     * @param UserEmail 用户登入邮箱
     * @param Password  用户密码
     * @return
     */
    @GetMapping("/Login")
    @ResponseBody
    public JsonResult Login(HttpServletRequest request, String UserEmail, String Password) {
        if (UserEmail == null && Password == null)
            return JsonResult.Fail();
        JsonResult jsonResult;
        jsonResult = userService.Login(UserEmail, Password);
        if (jsonResult.getData() != null) {
            HttpSession session = request.getSession(true);
            session.setMaxInactiveInterval(60 * 60 * 24 * 3);
            session.setAttribute("user", jsonResult.getData());
        }
        return jsonResult;
    }


    /**
     * 验证当前用户的session是否有效
     *
     * @param request
     * @return
     */
    @GetMapping("/CheckSession")
    @ResponseBody
    public boolean CheckSession(HttpServletRequest request) {
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

    @GetMapping("/GetUserName")
    @ResponseBody
    public JsonResult GetUserNameAndId(HttpServletRequest request) {
        HttpSession session;
        String UserName;
        String UserId;
        JsonResult jsonResult;
        try {
            session = request.getSession(false);
            User user = (User) session.getAttribute("user");
            UserName = user.getUserName();
            UserId = user.getUserId();
        } catch (Exception e) {
            return JsonResult.Fail();
        }
        jsonResult = JsonResult.OK();
        jsonResult.setData(UserName + "," + UserId);
        return jsonResult;
    }

    @GetMapping("/ExitLogin")
    @ResponseBody
    public JsonResult exitLogin(HttpServletRequest httpServletRequest) {
        HttpSession session;
        try {
            session = httpServletRequest.getSession(false);
            session.setMaxInactiveInterval(0);
            session.removeAttribute("user");
        } catch (Exception e) {
            System.out.println("session错误");
            return JsonResult.Fail();
        }
        return JsonResult.OK();
    }
}
