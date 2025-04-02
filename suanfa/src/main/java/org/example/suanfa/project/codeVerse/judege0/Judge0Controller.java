package org.example.suanfa.project.codeVerse.judege0;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/judge0")
@CrossOrigin(origins = "*") // 允许跨域
public class Judge0Controller {
    private final Judge0Service judge0Service;
    
    public Judge0Controller(Judge0Service judge0Service) {
        this.judge0Service = judge0Service;
    }
    
    @PostMapping("/submissions")
    public ResponseEntity<?> submitCode(@RequestBody SubmissionRequest request) {
        try {
            SubmissionResponse response = judge0Service.submitCode(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of("error", e.getMessage()));
        }
    }
    
    @GetMapping("/submissions/{token}")
    public ResponseEntity<?> getSubmissionResult(@PathVariable String token) {
        try {
            SubmissionResponse response = judge0Service.getSubmissionResult(token);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of("error", e.getMessage()));
        }
    }
}