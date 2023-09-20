package com.reactive.domain;

import org.springframework.lang.Nullable;

public enum AnalysisResultReason {
    NO_THREAT_DETECTED(0),
    INFECTED(1),
    SUSPICIOUS(2),
    FAILED(3),
    WHITE_LISTED(7),
    BLACK_LISTED(8),
    EXCEEDED_ARCHIVE_DEPTH(9),
    NOT_SCANNED(10),
    ENCRYPTED_ARCHIVE(12),
    EXCEEDED_ARCHIVE_SIZE(13),
    EXCEEDED_ARCHIVE_FILE_NUMBER(14),
    PASSWORD_PROTECTED_DOCUMENT(15),
    EXCEEDED_ARCHIVE_TIMEOUT(16),
    MISMATCH(17),
    POTENTIALLY_VULNERABLE_FILE(18),
    CANCELLED(19),
    SENSITIVE_DATA_FOUND(20),
    YARA_RULE_MATCHED(21),
    POTENTIALLY_UNWANTED(22),
    UNSUPPORTED_FILE_TYPE(23),
    IN_PROGRESS(255);

    private final int code;
    private static final AnalysisResultReason[] VALUES = values();
    private AnalysisResultReason(int code) {
        this.code = code;
    }

    public static AnalysisResultReason valueOf(int code) {
        AnalysisResultReason status = resolve(code);
        if (status == null) {
            throw new IllegalArgumentException("No matching constant for [" + code + "]");
        } else {
            return status;
        }
    }

    @Nullable
    public static AnalysisResultReason resolve(int code) {
        AnalysisResultReason[] var1 = VALUES;
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            AnalysisResultReason status = var1[var3];
            if (status.code == code) {
                return status;
            }
        }

        return null;
    }
}
