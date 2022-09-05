package ai.logzi.core.management.logmanagement.service.validation.factory;

import ai.logzi.core.management.logmanagement.service.dto.log.LogPipelineProcessorDto;
import org.springframework.stereotype.Service;

@Service
public interface LogPipelineProcessorValidationFactory {

    LogPipelineProcessorValidation create(final LogPipelineProcessorDto logPipelineProcessorDto) throws Exception;

}
