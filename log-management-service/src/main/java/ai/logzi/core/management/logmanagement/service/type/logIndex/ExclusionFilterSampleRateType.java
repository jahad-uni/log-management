package ai.logzi.core.management.logmanagement.service.type.logIndex;

import lombok.Getter;

@Getter
public enum ExclusionFilterSampleRateType {
    ALL_LOGS("all logs"),
    TRACE_ID("Trace Id");

    private final String code;

    ExclusionFilterSampleRateType(String code) {
        this.code = code;
    }
}
