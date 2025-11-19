//package com.lingo.chatbot.service;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.ai.document.Document;
//import org.springframework.ai.vectorstore.VectorStore;
//import org.springframework.stereotype.Service;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//@RequiredArgsConstructor
//public class RagService {
//
//    private final VectorStore vectorStore;
//
//    public String retrieve(String query) {
//        List<Document> results = vectorStore.similaritySearch(query);
//
//        return results.stream()
//                .map(Document::getText)
//                .collect(Collectors.joining("\n\n"));
//    }
//}
//
