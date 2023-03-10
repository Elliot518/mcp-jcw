package com.mcp.infrastructure.common.util.im.ding;

/**
 * @author : KG
 * description:
 * create date: 4:34 PM 2019/12/24
 * modified by:
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

/**
 * 钉钉机器人消息发送工具
 */
public class DingTalkTool {

    private static final Logger logger = LoggerFactory.getLogger(DingTalkTool.class);

    /**
     * OK 响应码
     */
    private static final int CODE_OK = 200;

    /**
     * OkHttpClient 可复用
     */
    private static final OkHttpClient HTTP_CLIENT = new OkHttpClient();

    /**
     * 修改为你的 webhook
     */
    //private static final String WEBHOOK = "https://oapi.dingtalk.com/robot/send?access_token=b52819040cda95aef37a1fe60878fd6ffb996daf2c6b5f65db2308c6f9f75320";

    /**
     * 异步发送消息
     *
     * @param message 消息
     */
    public static void send(BaseMessage message) {
        CompletableFuture.completedFuture(message)
                .thenAcceptAsync(DingTalkTool::sendSync);
    }

    /**
     * 同步发送消息
     *
     * @param message 消息
     */
    private static void sendSync(BaseMessage message) {
        // HTTP 消息体（编码必须为 utf-8）
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        RequestBody requestBody = RequestBody.create(mediaType, message.toRequestBody());

        // 创建 POST 请求
        Request request = new Request.Builder()
                .url(message.getUrl())
                .post(requestBody)
                .build();

        // 通过 HTTP 客户端发送请求
        HTTP_CLIENT.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call c, IOException e) {
                logger.error("发送消息失败，请查看异常信息", e);
            }

            @Override
            public void onResponse(Call c, Response r) throws IOException {
                int code = r.code();
                if (code != CODE_OK) {
                    logger.error("发送消息失败，code={}", code);
                    return;
                }

                ResponseBody responseBody = r.body();
                if (responseBody != null) {
                    JSONObject body = JSON.parseObject(responseBody.string());

                    int errCode = body.getIntValue("errcode");
                    if (errCode != 0) {
                        String errMsg = body.getString("errmsg");
                        logger.error("发送消息出现错误，errCode={}, errMsg={}", errCode, errMsg);
                    }
                }
            }
        });
    }
}
