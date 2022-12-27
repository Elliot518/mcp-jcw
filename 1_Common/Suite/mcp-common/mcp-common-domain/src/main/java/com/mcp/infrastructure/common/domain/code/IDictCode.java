package com.mcp.infrastructure.common.domain.code;

/**
 * @author KG
 * @description
 * @date Created in 2021年09月28日 9:53 PM
 * @modified_by
 */
public interface IDictCode<TKey, TValue> {
    /**
     * Get Key
     * @author KG
     * @date 2021/9/28 9:57 PM
     * @return TKey
     */
    TKey getKey();

    /**
     * Get Value
     * @author KG
     * @date 2021/9/28 9:57 PM
     * @return TValue
     */
    TValue getValue();
}
