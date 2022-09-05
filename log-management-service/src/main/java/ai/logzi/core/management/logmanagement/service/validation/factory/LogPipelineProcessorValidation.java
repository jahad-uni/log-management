package ai.logzi.core.management.logmanagement.service.validation.factory;

import ai.logzi.core.management.logmanagement.service.dto.log.LogPipelineProcessorDto;
import org.springframework.stereotype.Service;

@Service
public interface LogPipelineProcessorValidation {


    LogPipelineProcessorDto validate(final LogPipelineProcessorDto logPipelineProcessorDto) throws Exception;

}
