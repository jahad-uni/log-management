package ai.logzi.core.management.logmanagement.service.type.log;

import lombok.Getter;

@Getter
public enum LogPipelineCoreFilterType {

    IS_ENABLED("is_enabled"),
    IS_EMPTY("is_empty"),
    IS_READONLY("is_readonly"),
    TYPE("type"),
    LAST_UPDATED_TIME("last_updated_time"),
    LAST_UPDATED_BY("last_updated_by"),
    CONTAINS("contains");

    private final String code;

    LogPipelineCoreFilterType(String code) {
        this.code = code;
    }
}
