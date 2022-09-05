package ai.logzi.core.management.logmanagement.service.type.logMetric;


import lombok.Getter;

@Getter
public enum ComputeAggregationType {
    COUNT("count"),
    DISTRIBUTION("distribution");

    private final String code;


    ComputeAggregationType(String code){
        this.code = code;
    }
}
