package com.qiu.lstmpower.Impl;

import com.qiu.lstmpower.Dao.SettingDao;
import com.qiu.lstmpower.Service.SettingService;
import com.qiu.lstmpower.Util.JsonResult;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class SettingServiceImpl implements SettingService {
    @Resource
    private SettingDao settingDao;

    @Override
    public JsonResult ChangePassword(String NewPassword) {
        return null;
    }

    @Override
    public JsonResult ChangeCity(String UserId, String ADCode) {
        settingDao.ChangeCity(UserId, ADCode);
        return JsonResult.OK();
    }

    @Override
    public JsonResult GetCity(String UserId) {
        JsonResult jsonResult;
        String ADCode = settingDao.GetCity(UserId);
        if (ADCode == null) {
            settingDao.initSetting(UserId);
            jsonResult = JsonResult.Fail();
        } else {
            jsonResult = JsonResult.OK();
            jsonResult.setData(ADCode);
        }
        return jsonResult;
    }
}
