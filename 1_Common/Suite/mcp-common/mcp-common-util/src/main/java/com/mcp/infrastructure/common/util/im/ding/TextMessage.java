package com.mcp.infrastructure.common.util.im.ding;

import com.alibaba.fastjson.JSONObject;

/**
 * @author : KG
 * description: 文本消息
 * create date: 4:32 PM 2019/12/24
 * modified by:
 */

public class TextMessage extends BaseMessage {

    /**
     * 消息内容
     */
    private final String content;

    public TextMessage(String content) {
        super();
        this.content = content;
    }

    @Override
    public String toRequestBody() {
        // 消息体
        JSONObject msgBody = new JSONObject(3);

        // 消息类型为 text
        msgBody.put("msgtype", "text");

        // 消息内容
        JSONObject text = new JSONObject(1);
        text.put("content", content);
        msgBody.put("text", text);

        // 要 at 的人的电话号码
        JSONObject at = new JSONObject(2);
        at.put("isAtAll", isAtAll());
        at.put("atMobiles", getAtMobiles());
        msgBody.put("at", at);

        return msgBody.toJSONString();
    }
}

