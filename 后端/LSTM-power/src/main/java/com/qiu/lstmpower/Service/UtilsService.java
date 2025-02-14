package com.qiu.lstmpower.Service;

import com.qiu.lstmpower.Util.JsonResult;
import org.springframework.stereotype.Service;

@Service
public interface UtilsService {
    JsonResult GetHFWeather(String UserId);
}
