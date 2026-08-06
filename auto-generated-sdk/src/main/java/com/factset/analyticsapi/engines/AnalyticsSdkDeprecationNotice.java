/*
 * This file provides deprecation warnings to SDK consumers
 */
package com.factset.analyticsapi.engines;

import java.time.LocalDate;
import java.time.Month;

/**
 * Deprecation notice for the Analytics SDK
 */
@Deprecated(since = "6.3.1", forRemoval = true)
public class AnalyticsSdkDeprecationNotice {

    public static final LocalDate DEPRECATION_DATE = LocalDate.of(2026, Month.AUGUST, 31);
    public static final String REPLACEMENT_PACKAGE = "com.factset.sdk:FactSetAnalyticsAPI";
    public static final String MIGRATION_GUIDE_URL = "https://github.com/factset/enterprise-sdk";

    public static final String DEPRECATION_MESSAGE =
        "The Analytics SDK is deprecated effective 31-Aug-2026. " +
        "Please migrate to the Enterprise SDK (com.factset.sdk:FactSetAnalyticsAPI). " +
        "See https://github.com/factset/enterprise-sdk for details.";

    /**
     * Private constructor to prevent instantiation
     */
    private AnalyticsSdkDeprecationNotice() {
        // Utility class
    }
}
