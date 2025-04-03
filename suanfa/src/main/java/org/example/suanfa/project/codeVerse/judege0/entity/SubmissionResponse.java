package org.example.suanfa.project.codeVerse.judege0.entity;

public class SubmissionResponse {
    private String taskId;
    private String message;

    public SubmissionResponse(String taskId, String message) {
        this.taskId = taskId;
        this.message = message;
    }

    // Getters
    public String getTaskId() { return taskId; }
    public String getMessage() { return message; }
}