package org.example.suanfa.project.codeVerse.judege0.entity;

import lombok.Data;

@Data
public class SubmissionRequest {
    private int languageId;
    private String sourceCode;
    private String stdin;
}