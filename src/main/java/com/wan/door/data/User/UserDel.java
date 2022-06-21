package com.wan.door.data.User;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
@Data
public class UserDel {

    @NotNull(message = "用户名称参数没有传！")
    @Length(min = 5,max = 12,message = "用户名称长度至少为5位,小于12位！")
    private String name;
}
