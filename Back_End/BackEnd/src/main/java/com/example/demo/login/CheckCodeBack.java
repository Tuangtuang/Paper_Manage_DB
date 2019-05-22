package com.example.demo.login;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @program: demo
 * @description: 返回验证码
 * @author: tyq
 * @create: 2019-04-25 17:27
 **/
@Data
public class CheckCodeBack {
    @JsonProperty("time")
    String time;
    @JsonProperty("code")
    String code;
    @JsonProperty("inputCode")
    String inputCode;
    @JsonProperty("userId")
    String userId;

}
