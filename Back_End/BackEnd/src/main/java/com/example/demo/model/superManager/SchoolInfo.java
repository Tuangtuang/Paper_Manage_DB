package com.example.demo.model.superManager;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @program: demo
 * @description: 添加学校信息
 * @author: tyq
 * @create: 2019-05-22 22:20
 **/
@Data
public class SchoolInfo {
    @JsonProperty("school")
    String school;
    @JsonProperty("degree")
    String degree;
    @JsonProperty("userId")
    String userId;
}
