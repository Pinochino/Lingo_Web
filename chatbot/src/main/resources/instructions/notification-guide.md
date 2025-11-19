# Notification Guide for Chatbot

## Overview

This document guides the chatbot to create notifications based on user requests. The chatbot must map **notificationTypeName** to the correct **typeId** using the following JSON configuration:

[
{ "id": 2, "name": "WELCOME_USER", "description": "Welcome new account on the website", "defaultMail": true, "defaultApp": true },
{ "id": 3, "name": "TEST_COMPLETED", "description": "Notification when a user completes a test.", "defaultMail": false, "defaultApp": true },
{ "id": 4, "name": "LESSON_REMINDER", "description": "Reminds the user about an upcoming lesson.", "defaultMail": false, "defaultApp": true },
{ "id": 5, "name": "SYSTEM_MAINTENANCE", "description": "Notification about a scheduled system maintenance.", "defaultMail": false, "defaultApp": true },
{ "id": 6, "name": "COMMENT_REPLY", "description": "Notification when someone replies to your comment.", "defaultMail": false, "defaultApp": true },
{ "id": 7, "name": "COURSE_UPDATE", "description": "Notification when a new test is added to a course you are enrolled in.", "defaultMail": true, "defaultApp": true }
]

---

## Rules

1. **Mapping Notification Name to ID**
    - Use the `"name"` field in the JSON to identify the type requested by the user.
    - The corresponding `"id"` is the `notificationTypeId` to send in the notification request.

2. **Role-based restrictions**
    - **Admin users** can send notifications to **all users**.
    - **Normal users** can only send notifications **to themselves**.

3. **Fields to include in request**
    - `userId`: ID of the user to receive the notification
    - `notificationTypeId`: integer ID corresponding to the name in the JSON
    - `title`: short title for the notification
    - `content`: detailed message
4. **Don't ask user userId**
---

## Example Scenarios

### 1. Admin sends a system maintenance notification to all users
- `role = admin`
- `notificationTypeName = SYSTEM_MAINTENANCE`
- `notificationTypeId = 5`
- Can send to all users.

### 2. Normal user sends a test completion notification to themselves
- `role = user`
- `notificationTypeName = TEST_COMPLETED`
- `notificationTypeId = 3`
- Can only send to themselves (userId = their own ID).

---

## Instructions for Chatbot

1. Check the user role before creating the notification.
2. Map the notification name to its ID using the JSON above.
3. Validate that a normal user only sends notifications to themselves.
4. Include both `title` and `content` in the request.
5. Follow the default notification channels if needed (`defaultMail`, `defaultApp`), but the main focus is `notificationTypeId` and `userId`.
6. Respond with confirmation that notification has been sent.
