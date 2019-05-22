package com.example.demo.login;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @program: demo
 * @description: 验证码返回时间和验证码
 * @author: tyq
 * @create: 2019-04-25 16:32
 **/
@Data
public class CheckCode {
    @JsonProperty("time")
    String time;
    @JsonProperty("code")
    String code;
}
