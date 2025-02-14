package com.qiu.lstmpower.Controller;

import com.qiu.lstmpower.Entity.User;
import com.qiu.lstmpower.Service.UtilsService;
import com.qiu.lstmpower.Util.JsonResult;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/Utils")
public class UtilsController {
    @Resource
    UtilsService utilsService;

    @GetMapping("/GetWeather")
    @ResponseBody
    public JsonResult GetWeather(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) return JsonResult.Fail();
        User user =  (User)session.getAttribute("user");
        if (user == null) return JsonResult.Fail();
        return utilsService.GetHFWeather(user.getUserId());
    }

}
