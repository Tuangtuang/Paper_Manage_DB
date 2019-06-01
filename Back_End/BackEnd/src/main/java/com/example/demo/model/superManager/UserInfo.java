package com.example.demo.model.superManager;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.management.DescriptorKey;

/**
 * @program: demo
 * @description: 超级管理员添加用户信息
 * @author: tyq
 * @create: 2019-05-22 19:09
 **/
@Data
public class UserInfo {
    @JsonProperty("user_phone")
    String userPhone;
    @JsonProperty("password")
    String password;
    @JsonProperty("identity")
    String identity;
    @JsonProperty("name")
    String userName;
    @JsonProperty("user_id")
    String userId;
}
