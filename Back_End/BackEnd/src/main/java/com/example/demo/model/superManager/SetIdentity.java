package com.example.demo.model.superManager;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @program: demo
 * @description: 设置试题管理员
 * @author: tyq
 * @create: 2019-05-29 18:40
 **/
@Data
public class SetIdentity {
    @JsonProperty("setUserId")
    String setUserId;
    @JsonProperty("userId")
    String userId;
    @JsonProperty("type")
    String type;
}
