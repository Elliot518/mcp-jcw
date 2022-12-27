package com.mcp.infrastructure.common.util.im.ding;

/**
 * @author : KG
 * description: 抽象消息类型（方便将来扩展其他类型的消息）
 * create date: 4:30 PM 2019/12/24
 * modified by:
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class BaseMessage {

    private List<String> atMobiles;

    private boolean atAll;

    private String url;

    /**
     * 转为 JSON 格式的请求体
     *
     * @return 当前消息对应的请求体
     */
    public abstract String toRequestBody();

    public void addAtMobile(String atMobile) {
        if (atMobiles == null) {
            atMobiles = new ArrayList<>(1);
        }

        atMobiles.add(atMobile);
    }

    public void setAtAll(boolean atAll) {
        this.atAll = atAll;
    }

    public List<String> getAtMobiles() {
        return atMobiles != null ? atMobiles : Collections.emptyList();
    }

    public boolean isAtAll() {
        return atAll;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}


