package org.example.suanfa.project.codeVerse.judege0;





import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class Judge0Service {
    private static final String JUDGE0_URL = "http://113.44.169.164:2358";
    private final RestTemplate restTemplate;
    
    public Judge0Service(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }
    
    public SubmissionResponse submitCode(SubmissionRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        HttpEntity<SubmissionRequest> entity = new HttpEntity<>(request, headers);
        
        try {
            ResponseEntity<SubmissionResponse> response = restTemplate.postForEntity(
                JUDGE0_URL + "/submissions", 
                entity, 
                SubmissionResponse.class
            );
            return response.getBody();
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            throw new RuntimeException("Judge0 API 调用失败: " + e.getResponseBodyAsString());
        }
    }
    
    public SubmissionResponse getSubmissionResult(String token) {
        try {
            return restTemplate.getForObject(
                JUDGE0_URL + "/submissions/" + token, 
                SubmissionResponse.class
            );
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            throw new RuntimeException("获取执行结果失败: " + e.getResponseBodyAsString());
        }
    }
}