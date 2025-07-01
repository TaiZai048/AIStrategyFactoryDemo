package com.aetherial.service;

import com.aetherial.pojo.dto.GeneratorPromptDTO;

/**
 * @author TaiZai
 * @data 2025/6/25 16:57
 */
public interface AiStrategyFactoryService {

    String chatByAi(GeneratorPromptDTO dto);
}
