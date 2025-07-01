package com.aetherial.handler;

import com.aetherial.enums.AiTypeEnum;
import com.aetherial.pojo.dto.GeneratorPromptDTO;
import com.aetherial.pojo.vo.ChatVO;
import com.aetherial.properties.AiProperties;
import com.aetherial.utils.HttpUtils;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author TaiZai
 * @data 2025/6/25 17:25
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class DeepSeekTypeHandler implements AiTypeHandler {

    private final AiProperties aiProperties;

    private static final String CHAT_ENDPOINT = "/chat/completions";

    /**
     * 获取DeepSeek的模型类型枚举
     * @return
     */
    @Override
    public AiTypeEnum getHandlerType() {
        return AiTypeEnum.DS;
    }

    /**
     * 实际的对话方法
     * @param dto
     */
    @Override
    public ChatVO chat(GeneratorPromptDTO dto) {
        // 1. 构建请求头
        Map<String, String> headers = Map.of(
                "Authorization", "Bearer " + aiProperties.getDeepseek().getApiKey(),
                "Content-Type", "application/json"
        );

        // 2. 构造请求参数
        Map<String, Object> requestParams = Map.of(
                "prompt", dto.getPrompt(),
                "messages", dto.getChatMessage(),
                "model", dto.getAiType().getModelName()
        );

        // 3. 发送请求
        try {
            String result = HttpUtils.doPost4Json(aiProperties.getDeepseek().getBaseUrl() + CHAT_ENDPOINT,
                            Map.of("json", JSONObject.toJSONString(requestParams)),
                            headers);

            // 4. 解析响应
            JSONObject responseJson = JSONObject.parseObject(result);
            JSONArray choices = responseJson.getJSONArray("choices");
            if (choices == null || choices.isEmpty()) {
                return ChatVO.builder()
                        .errorMessage("AI服务异常: " + result)
                        .build();
            } else {
                String content = choices.getJSONObject(0)
                        .getJSONObject("message")
                        .getString("content");
                return ChatVO.builder()
                        .content(content)
                        .build();
            }
        } catch (Exception e) {
            log.error("DeepSeek AI服务请求失败: {}", e.getMessage(), e);
            throw new RuntimeException("AI服务出错: " + e.getMessage());
        }
    }
}
