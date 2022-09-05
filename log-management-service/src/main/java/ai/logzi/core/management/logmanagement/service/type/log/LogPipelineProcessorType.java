package ai.logzi.core.management.logmanagement.service.type.log;

import lombok.Getter;

@Getter
public enum LogPipelineProcessorType {

    GROK_PARSER("grok-parser"),
    DATE_REMAPPER("date-remapper"),
    PIPELINE("pipeline"),
    STATUS_REMAPPER("status-remapper"),
    SERVICE_REMAPPER("service-remapper"),
    MESSAGE_REMAPPER("message-remapper"),
    ATTRIBUTE_REMAPPER("attribute-remapper"),
    URL_PARSER("url-parser"),
    USER_AGENT_PARSER("user-agent-parser"),
    ARITHMETIC_PROCESSOR("arithmetic-processor"),
    STRING_BUILDER_PROCESSOR("string-builder-processor"),
    GEO_IP_PARSER("geo-ip-parser"),
    TRACE_ID_REMAPPER("trace-id-remapper"),
    CATEGORY_PROCESSOR("category-processor"),
    LOOKUP_PROCESSOR("lookup-processor");

    private final String code;

    LogPipelineProcessorType(String code) {
        this.code = code;
    }
}
