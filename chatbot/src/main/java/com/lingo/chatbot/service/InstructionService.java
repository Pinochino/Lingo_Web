package com.lingo.chatbot.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Service;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

@Service
@Slf4j
@RequiredArgsConstructor
public class InstructionService {

    private String instructions = "";

    @PostConstruct
    public void init() {
        try {
            PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            Resource[] resources = resolver.getResources("classpath:/instructions/*.md");

            StringBuilder sb = new StringBuilder();
            for (Resource res : resources) {
                try (InputStream is = res.getInputStream();
                     Scanner scanner = new Scanner(is, StandardCharsets.UTF_8)) {
                    while (scanner.hasNextLine()) {
                        sb.append(scanner.nextLine()).append("\n");
                    }
                    sb.append("\n---\n");
                }
            }
            instructions = sb.toString();
            log.info("Loaded instructions: {} characters", instructions.length());
        } catch (Exception e) {
            log.error("Failed to load instructions", e);
        }
    }

    public String getInstructions() {
        return instructions;
    }
}
