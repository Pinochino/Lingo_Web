package com.lingo.testservice.model.dto.request;

import com.lingo.testservice.model.Answer;
import com.lingo.testservice.model.MediaResource;
import com.lingo.testservice.model.Test;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReqQuestionDTO {
    String title;
    long point;
    // fe pass question id to save key
    long answerKey;
    String explanation;
    String part;
    Test test;
//    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
//    List<Answer> answers;
//    @OneToOne(mappedBy = "question", cascade = CascadeType.ALL)
//    MediaResource resource;
}
