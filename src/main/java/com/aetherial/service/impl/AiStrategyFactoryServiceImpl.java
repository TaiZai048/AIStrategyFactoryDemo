package com.aetherial.service.impl;

import com.aetherial.enums.AiTypeEnum;
import com.aetherial.factory.AiTypeHandlerFactory;
import com.aetherial.handler.AiTypeHandler;
import com.aetherial.pojo.dto.GeneratorPromptDTO;
import com.aetherial.service.AiStrategyFactoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author TaiZai
 * @data 2025/6/25 16:57
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class AiStrategyFactoryServiceImpl implements AiStrategyFactoryService {

    /**
     * 注入AI类型处理器工厂
     */
    private final AiTypeHandlerFactory aiTypeHandlerFactory;

    /**
     * 调用AI模型的统一入口
     * @param dto
     * @return
     */
    @Override
    public String chatByAi(GeneratorPromptDTO dto) {

        // 1. 获取策略器
        AiTypeHandler handler = aiTypeHandlerFactory.getHandler(dto.getAiType());

        // 2. 调用对应的处理器进行对话
        try {
            return handler.chat(dto).getContent();
        } catch (Exception e) {
            log.error("AI对话失败，类型：{}，错误信息：{}", dto.getAiType(), e.getMessage());
            return "AI对话失败，请稍后再试。";
        }
    }
}
