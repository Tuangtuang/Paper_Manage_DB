package com.example.demo.model.superManager;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @program: demo
 * @description: 返回学校主键
 * @author: tyq
 * @create: 2019-05-22 22:33
 **/
@Data
public class SchoolId {
    @JsonProperty("schoolId")
    String schoolId;
}
