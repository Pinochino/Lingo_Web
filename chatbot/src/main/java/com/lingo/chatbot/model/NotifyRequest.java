package com.lingo.chatbot.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NotifyRequest {
    String title;
    long notificationTypeId;
    String typeName;
    String message;
    String url;
}
