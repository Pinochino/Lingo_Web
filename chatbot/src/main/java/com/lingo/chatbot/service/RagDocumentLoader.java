//package com.lingo.chatbot.service;
//
//import jakarta.annotation.PostConstruct;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.ai.document.Document;
//import org.springframework.ai.vectorstore.VectorStore;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.core.io.Resource;
//import org.springframework.stereotype.Service;
//
//import java.util.*;
//
//@Service
//@Slf4j
//@RequiredArgsConstructor
//public class RagDocumentLoader {
//    @Value("classpath:/instructions/*.md")
//    private Resource[] resources;
//
//    private final VectorStore vectorStore;
//
//    @PostConstruct
//    public void init() {
//        List<Document> docs = new ArrayList<>();
//
//        for (Resource res : resources) {
//            try {
//                String text = new String(res.getInputStream().readAllBytes());
//                Document doc = new Document(text);
//                doc.getMetadata().put("source", res.getFilename());
//                docs.add(doc);
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }
//        }
//
//        vectorStore.add(docs);
//    }
//}
