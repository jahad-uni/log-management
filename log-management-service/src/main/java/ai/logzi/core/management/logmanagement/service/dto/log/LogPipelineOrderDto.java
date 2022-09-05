package ai.logzi.core.management.logmanagement.service.dto.log;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogPipelineOrderDto {

    private String userId;
    private List<String> pipeline_ids;
}
