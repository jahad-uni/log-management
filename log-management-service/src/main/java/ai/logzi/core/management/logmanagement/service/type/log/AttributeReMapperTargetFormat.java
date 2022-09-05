package ai.logzi.core.management.logmanagement.service.type.log;

import lombok.Getter;

@Getter
public enum AttributeReMapperTargetFormat {
    AUTO("auto"),
    STRING("string"),
    INTEGER("integer"),
    DOUBLE("double");

    private final String code;

    AttributeReMapperTargetFormat(String code) {
        this.code = code;
    }
}
