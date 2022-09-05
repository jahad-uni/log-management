package ai.logzi.core.management.logmanagement.service.dto.logMetric;

import ai.logzi.core.management.logmanagement.service.dto.log.FilterDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttributeDto {
    private ComputeDto compute;
    private List<GroupByDto> group_by;
    private FilterDto filter;
}
