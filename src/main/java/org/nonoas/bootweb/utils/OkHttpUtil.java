package org.nonoas.bootweb.utils;

import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;
import java.util.Map;

/**
 * @author Nonoas
 * @datetime 2022/4/3 12:42
 */
@Slf4j
@Component
public class OkHttpUtil {

    @Resource
    private OkHttpClient okHttpClient;

    /**
     * 发送git请求
     *
     * @param url     请求的url
     * @param queries 请求的参数，在浏览器？后面的数据，没有可以传null
     * @return 响应体数据
     */
    public String get(String url, Map<String, String> queries) {
        String responseBody = "";
        StringBuilder sb = new StringBuilder(url);
        if (queries != null && queries.keySet().size() > 0) {
            boolean firstFlag = true;
            for (Map.Entry<String, String> entry : queries.entrySet()) {
                if (firstFlag) {
                    sb.append("?").append(entry.getKey()).append("=").append(entry.getValue());
                    firstFlag = false;
                } else {
                    sb.append("&").append(entry.getKey()).append("=").append(entry.getValue());
                }
            }
        }
        Request request = new Request
                .Builder()
                .addHeader("Connection", "close")
                .url(sb.toString())
                .build();
        try (Response response = okHttpClient.newCall(request).execute()) {
            int status = response.code();
            if (status == 200) {
                return response.body().string();
            }
        } catch (Exception e) {
            log.error("okhttp put error >> ex = {}", e.getMessage());
        }
        return responseBody;
    }

    /**
     * 发送git请求
     *
     * @param url    请求的url
     * @param params post form 提交的参数
     * @return 响应体数据
     */
    public String post(String url, Map<String, String> params) {
        String responseBody = "";
        FormBody.Builder builder = new FormBody.Builder();
        //添加参数
        if (params != null && params.keySet().size() > 0) {
            for (String key : params.keySet()) {
                builder.add(key, params.get(key));
            }
        }
        Request request = new Request
                .Builder()
                .url(url)
                .post(builder.build())
                .build();
        try (Response response = okHttpClient.newCall(request).execute()) {
            int status = response.code();
            if (status == 200) {
                return response.body().string();
            }
        } catch (Exception e) {
            log.error("okhttp post error >> ex = {}", e.getMessage());
        }
        return responseBody;
    }

    /**
     * post 上传文件
     *
     * @param url      请求地址
     * @param params   参数
     * @param fileType 文件类型
     * @return 请求响应
     */
    public String postFile(String url, Map<String, Object> params, String fileType) {
        String responseBody = "";
        MultipartBody.Builder builder = new MultipartBody.Builder();
        //添加参数
        if (params != null && params.keySet().size() > 0) {
            for (String key : params.keySet()) {
                if (params.get(key) instanceof File) {
                    File file = (File) params.get(key);
                    builder.addFormDataPart(key, file.getName(), RequestBody.create(MediaType.parse(fileType), file));
                    continue;
                }
                builder.addFormDataPart(key, params.get(key).toString());
            }
        }
        Request request = new Request
                .Builder()
                .url(url)
                .post(builder.build())
                .build();
        try (Response response = okHttpClient.newCall(request).execute()) {
            int status = response.code();
            if (status == 200) {
                return response.body().string();
            }
        } catch (Exception e) {
            log.error("okhttp postFile error >> ex = {}", e.getMessage());
        }
        return responseBody;
    }
}