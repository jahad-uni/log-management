package ai.logzi.core.management.logmanagement.service.dto.logMetric;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogMetricPipelineDto {
    private String id;
    private String userId;
    private String name;
    private String type;
    private AttributeDto attributes;
    private String updatedBy;
    private String updatedAt;
}
