package com.reactive.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FileAnalysisResponse {
    private String fileName;
    private String dataId;
    private AnalysisResult result;
    private AnalysisResultReason resultReason;
    private String md5;
    private String sha1;
    private String sha256;
}