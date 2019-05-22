package com.example.demo.login;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @program: demo
 * @description: 登陆成功返回token
 * @author: tyq
 * @create: 2019-04-26 00:25
 **/
@Data
public class TokenResponse {
    @JsonProperty("token")
    String token;
    @JsonProperty("identity")
    String identity;
    @JsonProperty("userName")
    String userName;
    @JsonProperty("userId")
    String userId;


}
