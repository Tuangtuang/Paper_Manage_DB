package com.example.demo.model.login;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @program: demo
 * @description: 内部系统用户登陆界面
 * @author: tyq
 * @create: 2019-04-26 00:31
 **/
@Data
public class UserLogin {
    @JsonProperty("user_id")
    String userId;
    @JsonProperty("password")
    String userPassword;

}
