package ai.logzi.core.management.logmanagement.service.validation.impl;

import ai.logzi.core.management.logmanagement.service.constant.I18Constant;
import ai.logzi.core.management.logmanagement.service.dto.log.LogPipelineDto;
import ai.logzi.core.management.logmanagement.service.dto.log.LogPipelineProcessorDto;
import ai.logzi.core.management.logmanagement.service.exception.LogPipelineValidationException;
import ai.logzi.core.management.logmanagement.service.validation.LogPipelineValidation;
import ai.logzi.core.management.logmanagement.service.validation.factory.LogPipelineProcessorValidationFactory;
import ai.logzi.core.management.logmanagement.service.validation.util.LogPipelineValidationUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@AllArgsConstructor
@Getter
public class LogPipelineValidationImpl implements LogPipelineValidation {

    private final LogPipelineProcessorValidationFactory logPipelineProcessorValidationFactory;
    private final LogPipelineValidationUtil logPipelineValidationUtil;

    @Override
    public LogPipelineDto validateLogPipeline(final LogPipelineDto logPipelineDto) throws Exception {


        var allErrors = this.logPipelineValidationUtil
                .validateLogPipeline(logPipelineDto);

        var validatedLogPipelineProcessors = new ArrayList<LogPipelineProcessorDto>();
        // Check LogPipelineProcessors
        for (var processorDto : logPipelineDto.getProcessors()) {
           processorDto.setIndex(String.valueOf(logPipelineDto.getProcessors().indexOf(processorDto)));
            var validatedLogPipelineProcessorDto = logPipelineProcessorValidationFactory
                    .create(processorDto)
                    .validate(processorDto);
//            validatedLogPipelineProcessors.add(validatedLogPipelineProcessorDto);
            allErrors.addAll(validatedLogPipelineProcessorDto);
//            validatedLogPipelineProcessors.add(validatedLogPipelineProcessorDto);
//            allErrors.addAll(validatedLogPipelineProcessorDto.getFieldErrorDtoList());
        }
        if (!allErrors.isEmpty())
            throw new LogPipelineValidationException(I18Constant.LOG_PIPELINE_VALIDATION_ERROR.getCode(), allErrors);
//        logPipelineDto.setProcessors(validatedLogPipelineProcessors);
        return logPipelineDto;
    }
}
