package com.qiu.lstmpower.Service;

import com.qiu.lstmpower.Util.JsonResult;
import jakarta.mail.MessagingException;
import org.springframework.stereotype.Service;

@Service
public interface EmailService {
    //发送注册验证码
    JsonResult SendVerificationCode(String userEmail) throws MessagingException;
}
