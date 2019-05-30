package com.example.demo.model.paperManager;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @program: demo
 * @description: 自动组卷要求
 * @author: tyq
 * @create: 2019-05-29 22:44
 **/
@Data
public class AutoPaperRequest {
    @JsonProperty("secondKown")
    List<String> secondKnowledge;
    @JsonProperty("numChoose")
    String choose;
    @JsonProperty("numBlank")
    String blank;
    @JsonProperty("numAn")
    String answer;
    @JsonProperty("1")
    String difficult;
    @JsonProperty("userId")
    String userId;
}
