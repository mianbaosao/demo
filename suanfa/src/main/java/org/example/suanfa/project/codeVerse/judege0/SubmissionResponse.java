package org.example.suanfa.project.codeVerse.judege0;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubmissionResponse {
    private String token;
    private String status;
    private String stdout;
    private String stderr;
    private String compile_output;
    private String message;
}