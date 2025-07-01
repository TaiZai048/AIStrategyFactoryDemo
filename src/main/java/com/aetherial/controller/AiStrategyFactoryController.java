package com.aetherial.controller;

import com.aetherial.enums.AiTypeEnum;
import com.aetherial.pojo.dto.GeneratorPromptDTO;
import com.aetherial.service.AiStrategyFactoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author TaiZai
 * @data 2025/6/25 16:56
 */
@RequestMapping("/test")
@RestController
@RequiredArgsConstructor
@Slf4j
public class AiStrategyFactoryController {

    private final AiStrategyFactoryService aiStrategyFactoryService;

    /**
     * 测试使用deepseek的对话
     * @return
     */
    @GetMapping("/deepseek")
    public String chatByDeepSeek() {
        GeneratorPromptDTO dto = GeneratorPromptDTO.builder()
                .prompt("You are a helpful assistant")
                .chatMessage(List.of(
                        Map.of("role", "user", "content", "你是谁？")
                ))
                .aiType(AiTypeEnum.DS)
                .build();
        return aiStrategyFactoryService.chatByAi(dto);
    }

    /**
     * 测试使用Qwen的对话
     * @return
     */
    @GetMapping("/qwen")
    public String chatByQwen() {
        GeneratorPromptDTO dto = GeneratorPromptDTO.builder()
                .prompt("You are a helpful assistant")
                .chatMessage(List.of(
                        Map.of("role", "user", "content", "你是谁？")
                ))
                .aiType(AiTypeEnum.QW_TURBO)
                .build();
        return aiStrategyFactoryService.chatByAi(dto);
    }
}
