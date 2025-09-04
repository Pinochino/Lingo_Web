package com.lingo.testservice.model.dto.request;

import com.lingo.testservice.model.Question;
import com.lingo.testservice.model.Test;
import jakarta.annotation.Nullable;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReqMediaResourceDTO {

    String mediaUrl;
    @Nullable
    String description;
    Test test;
    Question question;
}
