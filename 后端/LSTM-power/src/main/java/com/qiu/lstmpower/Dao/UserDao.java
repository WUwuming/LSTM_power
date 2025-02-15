package com.qiu.lstmpower.Dao;


import com.qiu.lstmpower.Entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDao {
    void Register(User user);   //用户注册

    List<User> Existence(String UserEmail);   //判断该邮箱是否已经被使用

    User Login(String UserEmail,String Password); //用户登入
}
