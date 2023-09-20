package com.reactive.domain;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class OpeningBalancesInput {

    private static final String PREFIX = "onboarding/onboardingData/input/%s/%s/";

    private String organizationOID;
    @Builder.Default
    private String countryCode = "US";
    @Builder.Default
    private String currencyCode = "USD";
    private String sourceOrgID;
    private LocalDate asOfDate;

    public String getPrefix() {
        return String.format(PREFIX, sourceOrgID, asOfDate.toString());
    }

    public Organization getInput() {
        return Organization.builder()
            .organizationOID(organizationOID)
            .countryCode(countryCode)
            .currencyCode(currencyCode)
            .asOfDate(asOfDate).build();
    }
}
