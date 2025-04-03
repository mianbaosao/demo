package org.example.suanfa.project.codeVerse.judege0.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.checkerframework.checker.units.qual.A;

@Data
@NoArgsConstructor
public class SubmissionTask implements Serializable {
    private String taskId;
    private int languageId;
    private String sourceCode;
    private String stdin;

    public SubmissionTask(String taskId, int languageId, String sourceCode, String stdin) {
    }
}