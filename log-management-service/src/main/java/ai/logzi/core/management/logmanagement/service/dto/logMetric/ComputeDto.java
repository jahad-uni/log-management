package ai.logzi.core.management.logmanagement.service.dto.logMetric;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComputeDto {
    private String aggregation_type;
    private String path;
}
