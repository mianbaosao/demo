package org.example.suanfa.project.codeVerse.judege0.cofig;


import org.example.suanfa.project.codeVerse.judege0.Judge0Service;
import org.example.suanfa.project.codeVerse.judege0.entity.SubmissionRequest;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class SubmissionConsumer {
    @Resource
    private Judge0Service judge0Service;

    @RabbitListener(queues = "submission-queue")
    public void process(SubmissionRequest request) {
       String token= judge0Service.submitCode(request);
       judge0Service.submit(token);
    }
}