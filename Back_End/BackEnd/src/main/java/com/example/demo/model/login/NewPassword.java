package com.example.demo.model.login;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @program: demo
 * @description: 更新密码接口
 * @author: tyq
 * @create: 2019-05-29 10:42
 **/
@Data
public class NewPassword {
    @JsonProperty("phone")
    String phone;
    @JsonProperty("new_password")
    String new_password;
}
