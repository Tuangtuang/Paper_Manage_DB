package com.example.demo.model.superManager;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @program: demo
 * @description: 用户在数据库里的记录
 * @author: tyq
 * @create: 2019-05-22 20:24
 **/
@Data
public class UserId {
    @JsonProperty("id")
    String id;
}
