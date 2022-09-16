package ai.logzi.core.microservice.logmanagement.api.annotation.impl;

import ai.logzi.core.management.logmanagement.service.dto.log.LogPipelineDto;
import ai.logzi.core.management.logmanagement.service.mapper.LogPipelineProcessorDtoMapper;
import ai.logzi.core.management.logmanagement.service.validation.LogPipelineValidation;
import ai.logzi.core.management.logmanagement.service.validation.factory.LogPipelineProcessorValidationFactory;
import ai.logzi.core.management.logmanagement.service.validation.factory.impl.GrokParserProcessorValidation;
import ai.logzi.core.management.logmanagement.service.validation.util.LogPipelineValidationUtil;
import ai.logzi.core.microservice.logmanagement.api.annotation.LogPipelineValidator;
import ai.logzi.core.microservice.logmanagement.api.mapper.LogPipelineMapper;
import ai.logzi.core.microservice.logmanagement.api.model.log.LogPipeline;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

//@AllArgsConstructor
public class LogPipelineValidatorImpl implements ConstraintValidator<LogPipelineValidator, LogPipeline> {

    @Autowired
    private  LogPipelineMapper logPipelineMapper;
    @Autowired
    private  LogPipelineValidation logPipelineValidation;


    @Override
    public void initialize(LogPipelineValidator constraintAnnotation) {

    }

    @Override
    public boolean isValid(LogPipeline logPipeline, ConstraintValidatorContext context) {

        LogPipelineDto logPipelineDto = logPipelineMapper.toLogPipeLineDto(logPipeline);
        boolean isValid = true;

        try {
            logPipelineValidation.validateLogPipeline(logPipelineDto);
        } catch (Exception e) {
            isValid = false;
        }
        return isValid;
    }
}
