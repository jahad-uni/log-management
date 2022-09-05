package ai.logzi.core.management.logmanagement.service.dto.logIndex;

import ai.logzi.core.management.logmanagement.service.dto.log.FilterDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IndexFilterDto extends FilterDto {

    private int sample_rate;
    private String sample_rate_type;
}
