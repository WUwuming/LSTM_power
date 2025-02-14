package com.qiu.lstmpower.Controller;

import com.qiu.lstmpower.Entity.User;
import com.qiu.lstmpower.Service.SettingService;
import com.qiu.lstmpower.Util.JsonResult;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/Setting")
public class SettingController {
    @Resource
    SettingService settingService;

    @GetMapping("/ChangeCity")
    @ResponseBody
    public JsonResult ChangeCity(HttpServletRequest request, String ADCode) {
        if (ADCode == null)
            return JsonResult.Fail();

        HttpSession session = request.getSession(false);
        String UserId = null;
        try {
            User user = (User) session.getAttribute("user");
            UserId = user.getUserId();
        }catch (Exception e){
            System.out.println(e);
        }
        JsonResult jsonResult = settingService.ChangeCity(UserId, ADCode);
        return jsonResult;
    }

    @GetMapping("/GetCity")
    @ResponseBody
    public JsonResult GetCity(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        String UserId = null;
        try {
           User user = (User) session.getAttribute("user");
            UserId = user.getUserId();
        }catch (Exception e){
            System.out.println(e);
        }
        JsonResult jsonResult = settingService.GetCity(UserId);
        return jsonResult;
    }
}
