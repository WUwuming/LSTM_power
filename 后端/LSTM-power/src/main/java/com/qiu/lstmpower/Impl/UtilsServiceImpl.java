package com.qiu.lstmpower.Impl;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONParser;
import com.qiu.lstmpower.Dao.SettingDao;
import com.qiu.lstmpower.Service.UtilsService;
import com.qiu.lstmpower.Util.JsonResult;
import com.qiu.lstmpower.Util.Log;
import com.qiu.lstmpower.Util.transformLocationCode;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.GZIPInputStream;

@Service
public class UtilsServiceImpl implements UtilsService {

    @Resource
    transformLocationCode transformLocationCode;

    @Resource
    SettingDao settingDao;
    @Value("${HEFENG-API}")
    String HFAuthorization;

    @Override
    public JsonResult GetHFWeather( String UserId) {
        String ADCode = settingDao.GetCity(UserId);
        String LocationCode = transformLocationCode.getLocationID(ADCode);
        try {
            String url = "https://devapi.qweather.com/v7/weather/now?location=" + LocationCode;
            URL obj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("X-QW-Api-Key", HFAuthorization);
            connection.setRequestProperty("Accept", "application/json");
            int code = connection.getResponseCode();
            InputStream inputStream = new GZIPInputStream(connection.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
//            new Log().writeLog(UserId + "请求和风天气API，请求状态" + code);
            JsonResult jsonResult;
            if (code == 200) {
                jsonResult = JsonResult.OK();

                jsonResult.setData(response);

            } else {
                jsonResult = JsonResult.Fail();
            }
            return jsonResult;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
