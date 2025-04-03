package org.example.suanfa.project.codeVerse.judege0;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.example.suanfa.project.codeVerse.judege0.entity.SubmissionRequest;
import org.example.suanfa.project.codeVerse.judege0.entity.SubmissionResponse;
import org.example.suanfa.project.codeVerse.judege0.entity.SubmissionTask;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class Judge0Service {
    private static final AtomicInteger successCount = new AtomicInteger(0);
    private static final String JUDGE0_URL = "http://113.44.169.164:2358";
    private final RestTemplate restTemplate;

    public Judge0Service(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public String submitCode(SubmissionRequest request) {
        // 1. 参数校验
        if (request.getSourceCode() == null || request.getSourceCode().isEmpty()) {
            throw new IllegalArgumentException("sourceCode 不能为空");
        }
        // 2. 构造 JSON 请求体
        String jsonRequest = String.format(
                "{\"stdin\":\"\",\"language_id\":%d,\"source_code\":\"%s\"}",
                request.getLanguageId(), request.getSourceCode().replace("\"", "\\\"") // 处理 JSON 内部的引号
        );

        // 3. 设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // 4. 发送请求
        HttpEntity<String> requestEntity = new HttpEntity<>(jsonRequest, headers);
        ResponseEntity<Map> response = restTemplate.postForEntity(
                JUDGE0_URL + "/submissions",
                requestEntity,
                Map.class
        );

        // 5. 解析响应
        if (response.getBody() != null && response.getBody().containsKey("token")) {
            return (String) response.getBody().get("token");
        } else {
            throw new RuntimeException("提交代码失败，未返回 token");
        }
    }



    public void submit(String token) {
        try {
            // 构造请求 URL
            String url = JUDGE0_URL + "/submissions/" + token;

            // 发送 GET 请求
            String result = restTemplate.getForObject(url, String.class);

            // 记录成功请求次数
            int count = successCount.incrementAndGet();
            System.out.println("执行结果: " + result + " 这次是第 " + count + " 次请求");
        } catch (Exception e) {
            System.err.println("查询失败: " + e.getMessage());
        }
    }
}
