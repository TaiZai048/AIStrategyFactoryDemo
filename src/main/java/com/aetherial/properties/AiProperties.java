package com.aetherial.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author TaiZai
 * @data 2025/7/1 14:20
 */
@Component
@ConfigurationProperties(prefix = "ai")
@Data
public class AiProperties {

    /**
     * DeepSeek官网的配置
     */
    private DeepSeek deepseek;

    /**
     * 阿里云百炼平台的配置
     */
    private Qwen qwen;

    @Data
    public static class DeepSeek {
        /**
         * DeepSeek API Key
         */
        private String apiKey;

        /**
         * DeepSeek API URL
         */
        private String baseUrl;
    }

    @Data
    public static class Qwen {
        /**
         * Qwen API Key
         */
        private String apiKey;
    }
}
