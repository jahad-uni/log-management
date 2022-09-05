package ai.logzi.core.microservice.logmanagement.entity.logMetric;

import lombok.Data;

@Data
public class ComputeEntity {
    private String aggregation_type;
    private String path;
}
