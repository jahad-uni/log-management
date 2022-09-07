package ai.logzi.core.microservice.logmanagement.api.validation.impl;

import ai.logzi.core.management.logmanagement.service.dto.log.LogPipelineProcessorDto;
import ai.logzi.core.management.logmanagement.service.dto.log.processor.GrokParserProcessorDto;
import ai.logzi.core.management.logmanagement.service.mapper.LogPipelineProcessorDtoMapper;
import ai.logzi.core.management.logmanagement.service.validation.dto.FieldErrorDto;
import ai.logzi.core.management.logmanagement.service.validation.factory.LogPipelineProcessorValidationFactory;
import ai.logzi.core.management.logmanagement.service.validation.factory.impl.GrokParserProcessorValidation;
import ai.logzi.core.management.logmanagement.service.validation.util.LogPipelineValidationUtil;
import ai.logzi.core.microservice.logmanagement.api.mapper.LogPipelineMapper;
import ai.logzi.core.microservice.logmanagement.api.model.log.LogPipelineProcessor;
import lombok.AllArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

@AllArgsConstructor
public class OnlyOneProcessorValidatorImpl implements ConstraintValidator<ai.logzi.core.microservice.logmanagement.api.validation.OnlyOneProcessorValidator, LogPipelineProcessor> {


    private final LogPipelineMapper logPipelineMapper;
    private final LogPipelineProcessorValidationFactory logPipelineProcessorValidationFactory;
    private final LogPipelineProcessorDtoMapper logPipelineProcessorDtoMapper;
    private final LogPipelineValidationUtil logPipelineValidationUtil;
    private final GrokParserProcessorValidation grokParserProcessorValidation;

    @Override
    public void initialize(ai.logzi.core.microservice.logmanagement.api.validation.OnlyOneProcessorValidator constraintAnnotation) {
//        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(LogPipelineProcessor pipelineProcessor, ConstraintValidatorContext context) {

        if (pipelineProcessor == null) {
            throw new IllegalArgumentException("LogPipelineProcessor must not be null");
        }
        int processorCount = 0;
        if (pipelineProcessor.getArithmeticProcessor() != null) processorCount++;
        if (pipelineProcessor.getCategoryProcessor() != null) processorCount++;
        if (pipelineProcessor.getLookupProcessor() != null) processorCount++;
        if (pipelineProcessor.getGeoIpParserProcessor() != null) processorCount++;
        if (pipelineProcessor.getAttributeReMapper() != null) processorCount++;
        if (pipelineProcessor.getDateReMapper() != null) processorCount++;
        if (pipelineProcessor.getGrokParser() != null) {
            processorCount++;
            LogPipelineProcessorDto dto = logPipelineMapper.toLogPipeLineProcessorDto(pipelineProcessor);
            List<FieldErrorDto> errorDtoList = grokParserProcessorValidation.validate(dto);
            // add dynamically errors to validations properties
            System.out.println("validate");
        }
        if (pipelineProcessor.getMessageReMapper() != null) processorCount++;
        if (pipelineProcessor.getNestedPipelineProcessor() != null) processorCount++;
        if (pipelineProcessor.getServiceReMapper() != null) processorCount++;
        if (pipelineProcessor.getStatusReMapper() != null) processorCount++;
        if (pipelineProcessor.getStringBuilderProcessor() != null) processorCount++;
        if (pipelineProcessor.getTraceIdReMapperProcessor() != null) processorCount++;
        if (pipelineProcessor.getUrlParser() != null) processorCount++;
        if (pipelineProcessor.getUserAgentParser() != null) processorCount++;


        if (processorCount == 0 || processorCount > 1)
            return false;
        else
            return true;
    }
}
