package com.example.demo.model.superManager;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @program: demo
 * @description: 添加一级知识点
 * @author: tyq
 * @create: 2019-05-29 17:22
 **/
@Data
public class AddFirstKnowledge {
    @JsonProperty("firstKown")
    String firstKnowledge;
    @JsonProperty("userId")
    String userId;
    @JsonProperty("subject")
    String subjectId;
}
