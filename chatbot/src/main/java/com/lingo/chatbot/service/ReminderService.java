package com.lingo.chatbot.service;

import com.lingo.chatbot.httpClient.TestClient;
import com.lingo.chatbot.model.ReqBroadcast;
import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class ReminderService {
    TestClient testClient;
    @Tool(
            name = "getAllTests",
            description = """
        Get all tests of system and provide some to user when user require or
        remind user some test to do. Do not modify or remove URLs provided by the system. Always return the full raw URL,
                                             including localhost:5173, without altering its host, schema, or format.
    """
    )
    public String getAllTests() {
        return String.valueOf(testClient.getAll(0,10).getBody());
    }
}
