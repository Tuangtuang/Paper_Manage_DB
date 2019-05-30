package com.example.demo.model.paperInput;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @program: demo
 * @description: 返回id
 * @author: tyq
 * @create: 2019-05-30 21:19
 **/
@Data
public class ProblemResponse {
    @JsonProperty("problem_id")
    String problemId;
}
