package com.aetherial.handler;

import com.aetherial.enums.AiTypeEnum;
import com.aetherial.pojo.dto.GeneratorPromptDTO;
import com.aetherial.pojo.vo.ChatVO;
import com.aetherial.properties.AiProperties;
import com.alibaba.dashscope.aigc.generation.Generation;
import com.alibaba.dashscope.aigc.generation.GenerationParam;
import com.alibaba.dashscope.aigc.generation.GenerationResult;
import com.alibaba.dashscope.common.Message;
import com.alibaba.dashscope.common.Role;
import com.alibaba.fastjson2.JSONObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author TaiZai
 * @data 2025/6/25 17:27
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class AliQwenTypeHandler implements AiTypeHandler {

    private final AiProperties aiProperties;

    /**
     * 获取AliQwen的模型类型枚举
     * @return
     */
    @Override
    public AiTypeEnum getHandlerType() {
        return AiTypeEnum.QW_TURBO;
    }

    /**
     * 阿里Qwen模型实际的对话方法
     * @param dto
     */
    @Override
    public ChatVO chat(GeneratorPromptDTO dto) {
        // 1. 构建生成对象
        Generation gen = new Generation();
        Message systemMsg = Message.builder()
                .role(Role.SYSTEM.getValue())
                .content(dto.getPrompt())
                .build();
        Message userMsg = Message.builder()
                .role(Role.USER.getValue())
                .content(JSONObject.toJSONString(dto.getChatMessage()))
                .build();

        // 2. 构建生成参数
        GenerationParam param = GenerationParam.builder()
                .apiKey(aiProperties.getQwen().getApiKey())
                .model(dto.getAiType().getModelName())
                .messages(Arrays.asList(systemMsg, userMsg))
                .resultFormat(GenerationParam.ResultFormat.MESSAGE)
                .build();

        // 3. 调用生成方法
        try {
            GenerationResult result = gen.call(param);
            return ChatVO.builder()
                    .content(result.getOutput().getChoices().get(0).getMessage().getContent())
                    .build();
        } catch (Exception e) {
            log.error("AliQwen AI服务出错: {}", e.getMessage(), e);
            throw new RuntimeException("AI服务出错: " + e.getMessage());
        }
    }
}
