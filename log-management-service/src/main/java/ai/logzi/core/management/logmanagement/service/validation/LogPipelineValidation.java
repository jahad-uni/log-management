package ai.logzi.core.management.logmanagement.service.validation;

import ai.logzi.core.management.logmanagement.service.dto.log.LogPipelineDto;

public interface LogPipelineValidation {

    LogPipelineDto validateLogPipeline(final LogPipelineDto logPipelineDto) throws Exception;

}
