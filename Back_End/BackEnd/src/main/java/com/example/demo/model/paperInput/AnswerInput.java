package com.example.demo.model.paperInput;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @program: demo
 * @description: 输入答案
 * @author: tyq
 * @create: 2019-05-30 00:50
 **/
@Data
public class AnswerInput {
    @JsonProperty("userId")
    String userId;
    @JsonProperty("problem_id")
    String problemId;
    @JsonProperty("markdown_content")
    String content;
    @JsonProperty("html_content")
    String html_content;
}
