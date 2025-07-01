package com.aetherial.handler;

import com.aetherial.enums.AiTypeEnum;
import com.aetherial.pojo.dto.GeneratorPromptDTO;
import com.aetherial.pojo.vo.ChatVO;

/**
 * 策略接口
 *
 * @author TaiZai
 * @data 2025/6/25 17:14
 */
public interface AiTypeHandler {

    /**
     * 枚举身份的识别，就是获取当前的调用的模型是什么类型的
     * @return
     */
    AiTypeEnum getHandlerType();

    /**
     * 实际的对话方法 每类型的模型对话方式实现内容不同
     * @param dto
     */
    ChatVO chat(GeneratorPromptDTO dto);
}
