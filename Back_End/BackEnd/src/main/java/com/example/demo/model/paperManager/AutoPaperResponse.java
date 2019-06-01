package com.example.demo.model.paperManager;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @program: demo
 * @description: 试卷返回接口
 * @author: tyq
 * @create: 2019-05-30 00:18
 **/
@Data

public class AutoPaperResponse {
    @JsonProperty("choose")
    List<String> choose;
    @JsonProperty("blank")
    List<String> blank;
    @JsonProperty("answer")
    List<String> answer;
}
