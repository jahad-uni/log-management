package ai.logzi.core.management.logmanagement.service.dto.logIndex;

import ai.logzi.core.management.logmanagement.service.dto.log.FilterDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogIndexDto {

    private String id;
    private long daily_limit;
    private FilterDto filter;
    private String name;
    private long num_retention_days;
    private boolean is_rate_limited;
    private List<ExclusionFilterDto> exclusion_filters;



    private String userId;

    private String updatedBy;
    private LocalDateTime updatedAt;

}
