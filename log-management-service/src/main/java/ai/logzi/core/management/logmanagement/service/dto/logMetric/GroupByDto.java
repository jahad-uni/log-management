package ai.logzi.core.management.logmanagement.service.dto.logMetric;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupByDto {

    private String path;
    private String tag_name;
}
