package com.aetherial.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author TaiZai
 * @data 2025/7/1 15:22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatVO {
    /**
     * 返回的文本
     */
    private String content;

    /**
     * 错误信息
     */
    private String errorMessage;
}
