package com.lingo.chatbot;

import lombok.Getter;

@Getter
public enum NotificationType {
    WELCOME_USER(2, "Welcome new account on the website"),
    TEST_COMPLETED(3, "Notification when a user completes a test"),
    LESSON_REMINDER(4, "Reminds the user about an upcoming lesson"),
    SYSTEM_MAINTENANCE(5, "Notification about a scheduled system maintenance"),
    COMMENT_REPLY(6, "Notification when someone replies to your comment"),
    COURSE_UPDATE(7, "Notification when a new test is added to a course you are enrolled in");

    private final int id;
    private final String description;

    NotificationType(int id, String description) {
        this.id = id;
        this.description = description;
    }
}