package com.aetherial.enums;

import lombok.Getter;

/**
 * 模型类型枚举 类型标识 作为工厂查表的 key
 *
 * @author TaiZai
 * @data 2025/6/25 16:58
 */
@Getter
public enum AiTypeEnum {

    DS("deepseek-chat", "DeepSeek官网模型调用"),
    QW_TURBO("qwen-turbo", "阿里云百炼平台的Qwen Turbo模型调用");

    private final String modelName;
    private final String desc;

    AiTypeEnum(String modelName, String desc) {
        this.modelName = modelName;
        this.desc = desc;
    }

    public static AiTypeEnum getModelTypeEnum(String modelName) {
        for (AiTypeEnum aiTypeEnum : AiTypeEnum.values()) {
            if (aiTypeEnum.getModelName().equals(modelName)) {
                return aiTypeEnum;
            }
        }
        return null;
    }
}
