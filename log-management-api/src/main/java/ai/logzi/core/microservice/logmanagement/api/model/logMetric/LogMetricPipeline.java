package ai.logzi.core.microservice.logmanagement.api.model.logMetric;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LogMetricPipeline {
    private String id;
    private String name;
    private String type;
    private Attribute attributes;

    private String updatedBy;
    private String updatedAt;
}
