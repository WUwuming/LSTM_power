package com.qiu.lstmpower.Impl;

import com.qiu.lstmpower.Service.EmailService;
import com.qiu.lstmpower.Util.CodeGeneratorUtil;
import com.qiu.lstmpower.Util.JsonResult;
import jakarta.annotation.Resource;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class EmailServiceImpl implements EmailService {
    @Resource
    private JavaMailSenderImpl mailSender;
    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Value("${spring.mail.username}")
    private String SenderEmail;

    /**
     * 该方法用于发送验证码
     *
     * @param userEmail 收件人邮箱
     * @return
     * @throws MessagingException
     */
    @Override
    public JsonResult SendVerificationCode(String userEmail) throws MessagingException {
        JsonResult jsonResult;

        MimeMessage mimeMessage = mailSender.createMimeMessage();

        //生成随机验证码/长度为6
        String code = CodeGeneratorUtil.generateCode(6);
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        //设置邮件内容
        helper.setText("<p style='color:black'>Dear Customer 您的验证码为：</p></br><h1>" + code + "</h1></br><p>5分钟后过期，如果不是您的请忽略</p>");
        //设置邮件主题
        helper.setSubject("光伏发电系统验证码--验证码");
        System.out.println("正在发往邮箱:" + userEmail + "...");
        //收件人
        helper.setTo(userEmail);
        //发件人
        helper.setFrom(SenderEmail);
        //将邮件存入redis，有效时间5分钟
        redisTemplate.opsForValue().set(userEmail, code, Duration.ofMinutes(5));
        //发送邮件
        mailSender.send(mimeMessage);

        System.out.println("发送成功！");
        jsonResult = JsonResult.OK().setData("邮件已经发送");
        return jsonResult;
    }

}
