package com.mcp.util.cache.caffeine;

import com.github.benmanes.caffeine.cache.Cache;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * @author: KG
 * @description:
 * @date: Created in 2021年08月28日 12:59 PM
 * @modified by:
 */
public class CaffeineCacheUtilsTest {
    @Test
    public void whenSetStringShouldGetString() {
        String key = "mcp:cache:string";
        String cacheStr = "Master HaKu";
        Cache<String, String> cache = CaffeineCacheUtils.buildCache(1024, 1, TimeUnit.HOURS);
        cache.put(key, cacheStr);
        Assert.assertEquals(cacheStr, CaffeineCacheUtils.getCache(key, cache));
    }
}
