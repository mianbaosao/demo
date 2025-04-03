    package org.example.suanfa.project.codeVerse.judege0.controller;

    import org.example.suanfa.project.codeVerse.judege0.Judge0Service;

    import org.example.suanfa.project.codeVerse.judege0.entity.SubmissionRequest;
    import org.example.suanfa.project.codeVerse.judege0.entity.SubmissionResponse;

    import org.example.suanfa.project.codeVerse.judege0.entity.SubmissionResult;
    import org.example.suanfa.project.codeVerse.judege0.entity.SubmissionTask;
    import org.springframework.amqp.rabbit.core.RabbitTemplate;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.data.redis.core.RedisTemplate;
    import org.springframework.web.bind.annotation.*;

    import java.util.UUID;
    import java.util.concurrent.CompletableFuture;

    import jakarta.annotation.Resource;

    import com.google.common.util.concurrent.RateLimiter;

    @RestController
    @RequestMapping("/api/test")
    public class SubmissionController {
        @Resource
        private RateLimiter rateLimiter;
        @Resource
        private RabbitTemplate rabbitTemplate;
        @Resource
        private RedisTemplate<String, Object> redisTemplate;
        @Resource
        private Judge0Service judge0Service;

        @PostMapping("/test")
        public String submitTask2(@RequestBody SubmissionRequest request) {
            System.out.println(request);
            return judge0Service.submitCode(request);
        }

        @PostMapping("/submit")
        public void submitTask(@RequestBody SubmissionRequest request) {
            String taskId = UUID.randomUUID().toString();
            SubmissionTask task = new SubmissionTask(taskId, request.getLanguageId(), request.getSourceCode(),request.getStdin());
            // 初始化状态
            redisTemplate.opsForValue().set("submission:" + taskId, "PENDING");
            // 尝试直接处理（限流通过时）
            if (rateLimiter.tryAcquire()) {
                try {
                    redisTemplate.opsForValue().set("submission:" + taskId, "PROCESSING");
                    String token =judge0Service.submitCode(request);
                    judge0Service.submit(token);
                    redisTemplate.opsForValue().set("submission:" + taskId, "COMPLETED");
                } catch (Exception e) {
                    redisTemplate.opsForValue().set("submission:" + taskId, "FAILED");
                    // 失败后重新入队（可选）
                     rabbitTemplate.convertAndSend("submission-queue", request);
                }
            } else {
                // 限流不通过时入队
                rabbitTemplate.convertAndSend("submission-queue", request);
            }
        }

        @GetMapping("/result/{taskId}")
        public SubmissionResult getResult(@PathVariable String taskId) {
            String status = (String) redisTemplate.opsForValue().get("submission:" + taskId);
            return new SubmissionResult(taskId, status);
        }
    }
