package com.lingo.chatbot.service;

import com.lingo.chatbot.httpClient.NotifyClient;
import com.lingo.chatbot.model.NotifyRequest;
import com.lingo.chatbot.model.ReqBroadcast;
import com.lingo.common_library.dto.ReqNotificationPost;
import com.lingo.common_library.dto.ResNotification;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class BotNotificationService {

    final NotifyClient notifyClient;

    // injected dynamically from ChatbotService to avoid asking user
    String userId;

    @Tool(
            name = "notifyUser",
            description = """
            Notify a single user. DO NOT ask for userId — it is always handled automatically.
            Just provide notificationTypeId, typeName, title, message, and optional url.
        """
    )
    public ResNotification notifyUserByChatbot(NotifyRequest request) {

        ReqNotificationPost req = new ReqNotificationPost();
        req.setUserId(userId); // <-- never ask, always auto-filled
        req.setNotificationTypeId(request.getNotificationTypeId());
        req.setTitle(request.getTitle());
        req.setMessage(request.getMessage());
        req.setUrl(request.getUrl());
        req.setTypeName(request.getTypeName());

        return notifyClient.createNotification(req).getBody();
    }

    @Tool(
            name = "notifyAllUser",
            description = """
        Send a notification to ALL users in the system.
        Do NOT ask for userId.
        Not require: notificationTypeId (or typeName), title, message, and optional url, These information 
        is already in the system prompt.
    """
    )
    public String notifyAllUser(ReqBroadcast reqBroadcast) {
        return notifyClient.broadcastNotification(reqBroadcast).getBody();
    }
}
