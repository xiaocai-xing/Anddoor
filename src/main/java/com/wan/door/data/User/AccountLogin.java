package com.wan.door.data.User;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AccountLogin {
    @NotNull(message = "用户名称参数没有传！")
    @Length(min = 5,max = 12,message = "用户名称长度至少为5位,小于12位！")
    private String nameUser;


    @NotNull(message = "密码名称参数没有传！")
    @Length(min = 6,max = 12,message = "密码长度至少为6位,小于12位！")
    private String passwordUser;
}
