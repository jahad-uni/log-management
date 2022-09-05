package ai.logzi.core.management.logmanagement.service.dto.log.processor;

import ai.logzi.core.management.logmanagement.service.dto.log.LogPipelineDto;
import lombok.Data;

@Data
public class NestedLogPipelineProcessorDto extends BaseProcessorDto {

    private LogPipelineDto pipeline;
}
