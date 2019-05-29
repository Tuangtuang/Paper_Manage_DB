package com.example.demo.model.paperManager;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @program: demo
 * @description: 所有题目
 * @author: tyq
 * @create: 2019-05-29 20:46
 **/
@Data
public class Questions {
    @JsonProperty("id")
    String id;
    @JsonProperty("subject")
    String subject;
    @JsonProperty("content")
    String content;
}
