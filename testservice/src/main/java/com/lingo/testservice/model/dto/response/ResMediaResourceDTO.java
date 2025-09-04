package com.lingo.testservice.model.dto.response;

import com.lingo.testservice.model.Question;
import com.lingo.testservice.model.Test;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResMediaResourceDTO {
    long id;
    String mediaUrl;
    String description;
    Test test;
    Question question;
}
