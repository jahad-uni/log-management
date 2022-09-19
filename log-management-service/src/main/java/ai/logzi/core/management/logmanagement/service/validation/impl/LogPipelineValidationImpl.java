package ai.logzi.core.management.logmanagement.service.validation.impl;

import ai.logzi.core.microservice.logmanagement.common.constant.I18Constant;
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
    public LogPipelineDto validateLogPipeline(final LogPipelineDto logPipelineDto) throws LogPipelineValidationException,Exception {


        var allErrors = this.logPipelineValidationUtil
                .validateLogPipeline(logPipelineDto);

        var validatedLogPipelineProcessors = new ArrayList<LogPipelineProcessorDto>();
        // Check LogPipelineProcessors
        for (var processorDto : logPipelineDto.getProcessors()) {
           processorDto.setIndex(String.valueOf(logPipelineDto.getProcessors().indexOf(processorDto)));
            var fieldErrorDtoList = logPipelineProcessorValidationFactory
                    .create(processorDto)
                    .validate(processorDto);
//            validatedLogPipelineProcessors.add(fieldErrorDtoList);
            allErrors.addAll(fieldErrorDtoList);
//            validatedLogPipelineProcessors.add(fieldErrorDtoList);
//            allErrors.addAll(fieldErrorDtoList.getFieldErrorDtoList());
        }
        if (!allErrors.isEmpty())
            throw new LogPipelineValidationException(allErrors);
//        logPipelineDto.setProcessors(validatedLogPipelineProcessors);
        return logPipelineDto;
    }
}
