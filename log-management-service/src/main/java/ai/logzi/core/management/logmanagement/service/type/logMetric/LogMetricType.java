package ai.logzi.core.management.logmanagement.service.type.logMetric;

import lombok.Getter;

@Getter
public enum LogMetricType {
    LOGS_METRICS("logs_metrics");
    private final String code;

    LogMetricType(String code) {
        this.code = code;
    }
}
