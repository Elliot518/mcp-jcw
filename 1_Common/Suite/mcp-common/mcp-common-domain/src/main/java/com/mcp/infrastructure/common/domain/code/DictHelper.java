package com.mcp.infrastructure.common.domain.code;

/**
 * @author KG
 * @description
 * @date Created in 2021年09月28日 9:57 PM
 * @modified_by
 */
public class DictHelper {
    public static <TKey, TValue> TValue match(TKey code, IDictCode<TKey, TValue>[] items) {
        for (IDictCode<TKey, TValue> item : items) {
            if (item.getKey().equals(code)) {
                return item.getValue();
            }
        }

        return null;
    }
}
