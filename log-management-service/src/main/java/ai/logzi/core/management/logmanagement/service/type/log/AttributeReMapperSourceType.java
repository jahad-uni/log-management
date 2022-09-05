package ai.logzi.core.management.logmanagement.service.type.log;

import lombok.Getter;

@Getter
public enum AttributeReMapperSourceType {
    ATTRIBUTE("attribute"),
    TAG("tag");

    private final String code;
    AttributeReMapperSourceType(String code){
        this.code = code;
    }
}
