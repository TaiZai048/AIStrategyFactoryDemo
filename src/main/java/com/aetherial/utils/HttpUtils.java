package com.aetherial.utils;

import java.io.IOException;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * @author TaiZai
 * @data 2025/7/1 14:53
 */
@Slf4j
public class HttpUtils {

    /**
     * 接口超时时间
     */
    public static final int DEFAULT_TIMEOUT_MSEC = 300 * 1000;

    /**
     * 发送POST请求，传入JSON格式的参数
     * @param url 请求Url
     * @param paramMap 请求参数，必须包含一个键为"json"的键值对，其值为JSON字符串
     * @param headers 请求头
     * @return 响应内容字符串，JSON格式
     * @throws IOException 如果请求过程中发生IO异常
     */
    public static String doPost4Json(String url, Map<String, String> paramMap, Map<String, String> headers) throws IOException {
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";

        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);

            // 设置请求头
            if (headers != null) {
                headers.forEach(httpPost::setHeader);
            }

            // 设置请求体内容
            if (paramMap != null && paramMap.containsKey("json")) {
                StringEntity entity = new StringEntity(paramMap.get("json"), "utf-8");
                entity.setContentEncoding("utf-8");
                entity.setContentType("application/json");
                httpPost.setEntity(entity);
            }

            httpPost.setConfig(builderRequestConfig());

            // 执行http请求
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                log.error("请求异常：{}", e.getMessage());
            }
        }

        return resultString;
    }

    /**
     * 构建请求配置
     * @param timeout 超时时间，单位毫秒
     * @return RequestConfig 对象
     */
    private static RequestConfig builderRequestConfig(int timeout) {
        return RequestConfig.custom()
                .setConnectTimeout(timeout)
                .setConnectionRequestTimeout(timeout)
                .setSocketTimeout(timeout)
                .build();
    }

    /**
     * 保留原方法，使用默认超时时间
     * @return RequestConfig 对象
     */
    private static RequestConfig builderRequestConfig() {
        return builderRequestConfig(DEFAULT_TIMEOUT_MSEC);
    }

}
