package ai.logzi.core.management.logmanagement.service.type.standardAttribute;

import lombok.Getter;

@Getter
public enum StandardAttributeType {
    STRING("string"),
    BOOLEAN("boolean"),
    INTEGER("integer"),
    DOUBLE("double");

    private final String code;

    StandardAttributeType(String code) {
        this.code = code;
    }
}
