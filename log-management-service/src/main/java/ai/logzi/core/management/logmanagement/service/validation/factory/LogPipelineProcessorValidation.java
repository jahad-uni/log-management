package ai.logzi.core.management.logmanagement.service.validation.factory;

import ai.logzi.core.management.logmanagement.service.dto.log.LogPipelineProcessorDto;
import ai.logzi.core.management.logmanagement.service.validation.dto.FieldErrorDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LogPipelineProcessorValidation {


    List<FieldErrorDto> validate(final LogPipelineProcessorDto logPipelineProcessorDto) throws Exception;

}
