package com.qiu.lstmpower.Dao;


import com.qiu.lstmpower.Entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDao {
    void Register(User user);   //注册用户

    List<User> Existence(String UserEmail);   //判断该邮箱是否已经被使用
}
