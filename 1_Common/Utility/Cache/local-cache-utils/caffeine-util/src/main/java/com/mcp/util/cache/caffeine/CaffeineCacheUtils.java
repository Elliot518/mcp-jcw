package com.mcp.util.cache.caffeine;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.RemovalListener;
import lombok.extern.slf4j.Slf4j;


import java.util.concurrent.TimeUnit;

/**
 * @author: KG
 * @description: Caffeine本地缓存工具集
 * @date: Created in 2021年08月28日 11:53 AM
 * @modified by:
 */
@Slf4j
public class CaffeineCacheUtils {
    public static <T> Cache<String, T> buildCache(int maximumSize, int expires, TimeUnit expireUnit) {
        return Caffeine.newBuilder()
                // 数量上限
                .maximumSize(maximumSize)
                // 过期机制
                .expireAfterWrite(expires, expireUnit)
                // 弱引用key
                .weakKeys()
                // 弱引用value
                .weakValues()
                // 剔除监听
                .removalListener((RemovalListener<String, T>) (key, value, cause) ->
                        log.info("key: {}, value: {}, Delete Cache Reason: {}", key, value, cause.toString()))
                .build();
    }

    public static <T> void putCache(String key, T obj, Cache<String, T> cache) {
        cache.put(key, obj);
    }

    public static <T> T getCache(String key, Cache<String, T> cache) {
        return cache.getIfPresent(key);
    }
}
