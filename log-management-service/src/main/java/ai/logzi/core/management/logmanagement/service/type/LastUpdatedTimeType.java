package ai.logzi.core.management.logmanagement.service.type;

import lombok.Getter;

@Getter
public enum LastUpdatedTimeType {

    LAST_DAY("last_day"),
    LAST_THREE_DAYS("last_three_days"),
    LAST_WEEK("last_week"),
    LAST_MONTH("last_month"),
    LAST_YEAR("last_year");

    private final String code;

    LastUpdatedTimeType(String code) {
        this.code = code;
    }
}
