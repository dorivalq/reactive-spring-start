package com.reactive.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginResponse {
    @JsonProperty("oms-csrf-token")
    private String omsCsrfToken;

    @JsonProperty("session_id")
    private String sessionId;

    @JsonProperty("oms-csrf-token")
    public String getOmsCsrfToken() {
        return omsCsrfToken;
    }

    @JsonProperty("oms-csrf-token")
    public void setOmsCsrfToken(String omsCsrfToken) {
        this.omsCsrfToken = omsCsrfToken;
    }

    @JsonProperty("session_id")
    public String getSessionId() {
        return sessionId;
    }

    @JsonProperty("session_id")
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
