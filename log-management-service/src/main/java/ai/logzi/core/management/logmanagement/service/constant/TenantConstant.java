package ai.logzi.core.management.logmanagement.service.constant;

import lombok.Getter;

@Getter
public enum TenantConstant {

    SHARED("shared");


    final String code;

    TenantConstant(String code) {
        this.code = code;
    }
}
