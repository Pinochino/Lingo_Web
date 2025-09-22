package com.lingo.attempt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResAttemptShortDTO {
  private Long attemptId;
  private Long quizId;
  private Date submittedAt;
  private Long score;
  private Long timeTaken;
}
