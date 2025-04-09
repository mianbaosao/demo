package org.example.suanfa.project.codeVerse.judege0;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import lombok.extern.slf4j.Slf4j;

import org.example.suanfa.project.codeVerse.judege0.entity.SubmissionRequest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.excel.EasyExcel;

@Service
@Slf4j
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

    public Object upload(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("上传文件为空");
        }

        try {
            // 读取 Excel 第一张 sheet 的内容（默认）
            List<List<String>> result = EasyExcel.read(file.getInputStream())
                    .sheet() // 默认读取第一个 sheet
                    .headRowNumber(0) // 无表头，全部按 List<String> 读入
                    .doReadSync(); // 同步读取全部内容

            log.info("Excel 上传解析结果: {}", result);

            // 这里可以返回前 10 条记录
            return result.size() > 10 ? result.subList(0, 10) : result;

        } catch (IOException e) {
            log.error("读取 Excel 失败", e);
            throw new RuntimeException("读取 Excel 文件失败，请检查文件格式是否正确");
        }
    }

}
