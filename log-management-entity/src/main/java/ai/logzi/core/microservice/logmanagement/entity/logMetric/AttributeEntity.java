package ai.logzi.core.microservice.logmanagement.entity.logMetric;

import ai.logzi.core.microservice.logmanagement.entity.share.FilterEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class AttributeEntity {
    private ComputeEntity compute;
    private List<GroupByEntity> group_by;
    private FilterEntity filter;
}
