package com.lingo.chatbot.service;

import com.lingo.chatbot.httpClient.NotifyClient;
import com.lingo.chatbot.httpClient.TestClient;
import com.lingo.chatbot.model.ChatRequest;
import com.lingo.common_library.dto.ReqNotificationPost;
import com.lingo.common_library.dto.ResNotification;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.chat.memory.repository.jdbc.JdbcChatMemoryRepository;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.content.Media;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@Service
@FieldDefaults(level= AccessLevel.PRIVATE)
public class ChatbotService {

    final ChatClient chatClient;
    final JdbcChatMemoryRepository jdbcChatMemoryRepository;
    final ChatMemory chatMemory;
    final NotifyClient notifyClient;
    final InstructionService instructionService;
    final TestClient testClient;
    String systemPrompt = """
You are the official AI assistant for Lingo - English learning website,
you also are Ielts tutor and can give user some recommendation about english
like answer question of a test, give user guildline.
""";

    public ChatbotService(
            ChatClient.Builder chatClient,
            JdbcChatMemoryRepository jdbcChatMemoryRepository,
            ChatMemory chatMemoryInit,
            NotifyClient notifyClient,
            InstructionService instructionService,
            TestClient testClient
    ) {
        this.chatMemory = chatMemoryInit;
        this.jdbcChatMemoryRepository = jdbcChatMemoryRepository;
        this.notifyClient = notifyClient;
        this.instructionService = instructionService;
        this.testClient=testClient;
        ChatMemory chatMemory = MessageWindowChatMemory.builder()
                .chatMemoryRepository(jdbcChatMemoryRepository)
                .maxMessages(36)
                .build();

        this.chatClient = chatClient
                .defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory).build())
                .build();
    }

    public String chat(ChatRequest request) {

        String conversationId = request.getUserId();

        BotNotificationService tools = new BotNotificationService(notifyClient);
        tools.setUserId(conversationId);

        String finalPrompt = systemPrompt +
                "\n\nInstruction from system documents:\n" +
                instructionService.getInstructions();

        Prompt prompt = new Prompt(
                new SystemMessage(finalPrompt),
                new UserMessage(request.getMessage())
        );

        return chatClient
                .prompt(prompt)
                .tools(tools, new ReminderService(testClient))
                .advisors(spec -> spec.param(ChatMemory.CONVERSATION_ID, conversationId))
                .call()
                .content();
    }

    public String chatWithMedia(MultipartFile file, String message, String userId) {

        BotNotificationService tools = new BotNotificationService(notifyClient);
        tools.setUserId(userId); // <-- same fix here

        Media media = Media.builder()
                .mimeType(MimeTypeUtils.parseMimeType(file.getContentType()))
                .data(file.getResource())
                .build();

        String finalPrompt = systemPrompt +
                "\n\nInstruction from system documents:\n" +
                instructionService.getInstructions();

        return chatClient
                .prompt()
                .tools(tools)  // <-- FIXED (notifyClient was wrong)
                .advisors(spec -> spec.param(ChatMemory.CONVERSATION_ID, userId))
                .system(finalPrompt)
                .user(u -> u.media(media).text(message))
                .call()
                .content();
    }

    public List<Message> getConversationMessage(String id) {
        return chatMemory.get(id);
    }
}
