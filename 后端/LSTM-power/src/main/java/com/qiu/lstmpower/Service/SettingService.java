package com.qiu.lstmpower.Service;

import com.qiu.lstmpower.Util.JsonResult;
import org.springframework.stereotype.Service;

@Service
public interface SettingService {
    JsonResult ChangePassword(String NewPassword);
    JsonResult ChangeCity(String UserId,String ADCode);
    JsonResult GetCity(String ADCode);
}
