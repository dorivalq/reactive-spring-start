package com.reactive.domain;

import lombok.Builder;
import lombok.Value;
import java.time.LocalDate;

@Value
@Builder(toBuilder = true)
public class Organization {

    private String organizationOID;
    private String countryCode;
    private String currencyCode;
    private LocalDate asOfDate;
}
