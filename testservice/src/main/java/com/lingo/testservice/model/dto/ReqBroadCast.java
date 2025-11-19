package com.lingo.testservice.model.dto;

import lombok.Data;

@Data
public class ReqBroadCast {
  private long notificationTypeId;
  private String title;
  private String message;
  private String url;
}
