package com.lingo.chatbot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class ReqBroadcast {
  private long notificationTypeId;
  private String title;
  private String message;
  private String url;
}
