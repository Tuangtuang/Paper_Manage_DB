package com.example.demo.model.superManager;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @program: demo
 * @description:
 * @author: tyq
 * @create: 2019-05-29 17:30
 **/
@Data
public class FirstKnowledgeIdResponse {
    @JsonProperty("firstKownId")
    String fisrtKnowledgeId;
}
