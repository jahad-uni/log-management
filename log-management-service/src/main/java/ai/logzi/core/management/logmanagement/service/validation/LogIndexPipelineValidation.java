package ai.logzi.core.management.logmanagement.service.validation;

import ai.logzi.core.management.logmanagement.service.dto.logIndex.LogIndexDto;

public interface LogIndexPipelineValidation {
    LogIndexDto validateLogIndex(final LogIndexDto logIndexDto) throws Exception;
}
