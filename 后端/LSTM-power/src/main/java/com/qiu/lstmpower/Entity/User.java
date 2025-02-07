package com.qiu.lstmpower.Entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String UserName;    //用户名，默认(用户)
    private String UserId;    //用户ID，通过雪花算法自动生成
    private String  UserEmail;  //用户邮箱

    private String Password;    //用户密码，通过算法加密后
    private char level;     //用户权限 0-免费用户 1-月费用户 6-管理员
}
