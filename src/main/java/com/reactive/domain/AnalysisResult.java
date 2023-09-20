package com.reactive.domain;

import java.util.List;

public enum AnalysisResult {
    CLEAN,
    REJECTED;

    public static final List<AnalysisResultReason> CLEAN_RESULTS = List.of(AnalysisResultReason.WHITE_LISTED, AnalysisResultReason.NO_THREAT_DETECTED);

    private AnalysisResult() {
    }
}
