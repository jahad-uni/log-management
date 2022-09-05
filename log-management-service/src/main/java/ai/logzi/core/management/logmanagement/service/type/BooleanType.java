package ai.logzi.core.management.logmanagement.service.type;

import lombok.Getter;

@Getter
public enum BooleanType {

    TRUE("true"),
    FALSE("false");

    private final String code;

    BooleanType(String code) {
        this.code = code;
    }
}
