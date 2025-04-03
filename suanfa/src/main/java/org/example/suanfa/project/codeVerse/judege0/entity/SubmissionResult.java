package org.example.suanfa.project.codeVerse.judege0.entity;

 public class SubmissionResult {
    private String taskId;
    private String status;

    public SubmissionResult(String taskId, String status) {
        this.taskId = taskId;
        this.status = status;
    }

    // Getters
    public String getTaskId() { return taskId; }
    public String getStatus() { return status; }
}