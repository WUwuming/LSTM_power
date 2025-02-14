package com.qiu.lstmpower.Dao;

import com.qiu.lstmpower.Entity.User;
import com.qiu.lstmpower.Entity.UserSetting;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SettingDao {
    List ExistenceSetting(String UserId);
    void initSetting(String UserId);
    UserSetting GetSetting(String UserId);
    void ChangeCity(String UserId, String ADCode);

    String GetCity(String UserId);

}
