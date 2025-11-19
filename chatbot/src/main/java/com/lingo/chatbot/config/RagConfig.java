//package com.lingo.chatbot.config;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.ai.document.Document;
//import org.springframework.ai.embedding.EmbeddingModel;
//import org.springframework.ai.vectorstore.VectorStore;
//import org.springframework.ai.vectorstore.redis.RedisVectorStore;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.Resource;
//import java.util.List;
//
//@Configuration
//@RequiredArgsConstructor
//@Slf4j
//public class RagConfig {
//
//    private final EmbeddingModel embeddingModel;
////    private final VectorStore vectorStore;
//    private final RedisVectorStore redisVectorStore;
//
//    @Bean
//    public VectorStore vectorStore() {
//        return redisVectorStore;
//    }
//}
