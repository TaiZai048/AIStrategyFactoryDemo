package com.aetherial.factory;

import com.aetherial.enums.AiTypeEnum;
import com.aetherial.handler.AiTypeHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 策略工厂 负责按类型枚举 → 返回对应 Handler 实例，屏蔽创建与管理细节，可做缓存或注入
 *
 * @author TaiZai
 * @data 2025/6/25 17:31
 */
@Component
@RequiredArgsConstructor
public class AiTypeHandlerFactory implements InitializingBean {

    private final List<AiTypeHandler> aiTypeHandlerList;

    private final Map<AiTypeEnum, AiTypeHandler> handlerMap = new HashMap<>();

    /**
     * 根据类型获取AI对话的处理器
     * @param aiType
     * @return
     */
    public AiTypeHandler getHandler(AiTypeEnum aiType) {
        AiTypeHandler handler = handlerMap.get(aiType);
        if (handler == null) {
            throw new IllegalArgumentException("No handler found for ai type: " + aiType);
        }
        return handler;
    }

    /**
     * 初始化枚举类与策略处理器的对应关系
     * springboot中实现InitializingBean接口并重写afterPropertiesSet方法，可以在Bean属性设置完成后执行自定义逻辑，
     * 例如初始化一些数据或执行一些操作。
     */
    @Override
    public void afterPropertiesSet() {
        // 初始化枚举类与策略处理器的对应关系
        for (AiTypeHandler aiTypeHandler : aiTypeHandlerList) {
            handlerMap.put(aiTypeHandler.getHandlerType(), aiTypeHandler);
        }
    }
}
