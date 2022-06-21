package com.wan.door.data.User;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class UserAdd {
    @NotNull(message = "用户名称参数没有传！")
    @Length(min = 5,max = 12,message = "用户名称长度至少为5位,小于12位！")
    private String name;

    @NotNull(message = "密码名称参数没有传！")
    @Length(min = 6,max = 12,message = "密码长度至少为6位,小于12位！")
    private String password;

    @NotNull(message = "所属业务参数没有传！")
    @Length(min = 3,max = 22,message = "用户名称长度至少为3位,小于22位！")
    private String department;

    @NotNull(message = "职级参数没有传！")
    @Pattern(regexp = "((^管理员$|^用户$))",message = "职级必须是枚举其中一个值！")
    private String rank;

//    @Email(message = "邮箱格式不正确！")
    @NotNull(message = "邮箱参数没有传！")
    @Length(min = 6,max = 22,message = "密码长度至少为6位,小于22位！")
    private String email;
}
