package com.example.demo.model.paperInput;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @program: demo
 * @description: 试题解析
 * @author: tyq
 * @create: 2019-05-30 20:40
 **/
@Data
public class SolutionInput {
    @JsonProperty("problem_id")
    String problemId;
    @JsonProperty("userId")
    String userId;
    @JsonProperty("markdown_content")
    String markdownContent;
    @JsonProperty("html_content")
    String htmlContent;
}
