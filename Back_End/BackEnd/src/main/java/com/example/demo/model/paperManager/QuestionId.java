package com.example.demo.model.paperManager;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @program: demo
 * @description: 题目号码
 * @author: tyq
 * @create: 2019-05-29 22:18
 **/
@Data
public class QuestionId {
    @JsonProperty("userId")
    String userId;
    @JsonProperty("questionId")
    String questionId;
}
