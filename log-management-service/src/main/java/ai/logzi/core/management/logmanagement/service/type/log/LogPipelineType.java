package ai.logzi.core.management.logmanagement.service.type.log;

import lombok.Getter;

@Getter
public enum LogPipelineType {

    INTEGRATION("integration"),
    CUSTOM("custom");

    private final String code;

    LogPipelineType(String code) {
        this.code = code;
    }
}
