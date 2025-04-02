package org.example.suanfa.project.codeVerse.judege0;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubmissionRequest {
    private Integer language_id;
    private String source_code;
    private String stdin;
}

