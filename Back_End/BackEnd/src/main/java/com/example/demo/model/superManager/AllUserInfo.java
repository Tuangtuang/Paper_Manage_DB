package com.example.demo.model.superManager;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @program: demo
 * @description: 所用的用户信息
 * @author: tyq
 * @create: 2019-05-29 14:29
 **/
@Data
public class AllUserInfo {
    @JsonProperty("id")
    String id;
    @JsonProperty("phone")
    String phone;
    @JsonProperty("other")
    String userName;
    @JsonProperty("identity")
    String identity;
}
