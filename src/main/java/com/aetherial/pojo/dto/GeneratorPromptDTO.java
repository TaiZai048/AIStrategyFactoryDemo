package com.aetherial.pojo.dto;

import com.aetherial.enums.AiTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * 请求模型时需要提供的参数
 *
 * @author TaiZai
 * @data 2025/6/25 17:19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GeneratorPromptDTO {

    /**
     * 提示词
     */
    private String prompt;

    /**
     * 聊天记录
     */
    private List<Map<String, Object>> chatMessage;

    /**
     * 模型枚举
     */
    private AiTypeEnum aiType;
}
