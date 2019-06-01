package com.example.demo.model.paperManager;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @program: demo
 * @description: 问题内容
 * @author: tyq
 * @create: 2019-05-30 13:59
 **/
@Data
public class ProblemName {
    @JsonProperty("userId")
    String userId;
    @JsonProperty("school_id")
    String schoolId;
    @JsonProperty("grade")
    String grade;
    @JsonProperty("subject_id")
    String subjectId;
    @JsonProperty("type_id")
    String typeId;
    @JsonProperty("first_knowledge_id")
    String firstKnowledgeId;
    @JsonProperty("second_knowldge_id")
    String secondKnowledgeId;
    @JsonProperty("hard_level")
    String hardLevel;
    @JsonProperty("markdown_content")
    String markdownContent;
    @JsonProperty("html_content")
    String htmlContent;

}
