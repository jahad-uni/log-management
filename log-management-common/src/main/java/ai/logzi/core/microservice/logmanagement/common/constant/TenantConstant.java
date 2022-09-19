package ai.logzi.core.microservice.logmanagement.common.constant;

import lombok.Getter;

@Getter
public enum TenantConstant {

    SHARED("shared");


    final String code;

    TenantConstant(String code) {
        this.code = code;
    }
}
