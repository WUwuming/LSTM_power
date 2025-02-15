package com.qiu.lstmpower.Controller;

import com.qiu.lstmpower.Service.EmailService;
import com.qiu.lstmpower.Util.JsonResult;
import jakarta.annotation.Resource;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/Mail")
public class EmailController {
    @Resource
    EmailService emailService;

    //往邮箱发送验证码
    @GetMapping("/GetVerificationCode")
    @ResponseBody
    public JsonResult GetVerificationCode(String userEmail){
        JsonResult jsonResult;
        if (userEmail == null)
            return JsonResult.Fail();

        try {
            jsonResult =  emailService.SendVerificationCode(userEmail);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        return  jsonResult;
    }
}
