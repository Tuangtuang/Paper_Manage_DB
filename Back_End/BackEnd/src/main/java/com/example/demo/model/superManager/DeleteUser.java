package com.example.demo.model.superManager;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @program: demo
 * @description:
 * @author: tyq
 * @create: 2019-05-29 11:14
 **/
@Data
public class DeleteUser {
    @JsonProperty("userId")
    String userId;
    @JsonProperty("deleteUserId")
    String deleteUserId;
}
