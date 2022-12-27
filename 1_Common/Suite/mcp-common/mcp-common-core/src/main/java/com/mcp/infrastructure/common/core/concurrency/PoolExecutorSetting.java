package com.mcp.infrastructure.common.core.concurrency;

import lombok.Builder;
import lombok.Data;

/**
 * @author: KG
 * @description:
 * @date: Created in 16:29 2022/4/24
 * @modified by:
 */
@Data
@Builder
public class PoolExecutorSetting {
    private int corePoolSize;

    private int maximumPoolSize;

    private long keepAliveTime;

    private int blockingQueueCapacity;
}
