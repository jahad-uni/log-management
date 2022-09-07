package ai.logzi.core.microservice.logmanagement.api.validation.impl;

import ai.logzi.core.microservice.logmanagement.api.model.log.LogPipelineProcessor;
import ai.logzi.core.microservice.logmanagement.api.validation.OnlyOneProcessor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class OnlyeOneProcessorValidator implements ConstraintValidator<OnlyOneProcessor, LogPipelineProcessor> {

    @Override
    public void initialize(OnlyOneProcessor constraintAnnotation) {
//        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(LogPipelineProcessor value, ConstraintValidatorContext context) {

        if (value == null) {
            throw new IllegalArgumentException("@AddressAnnotation only applies to Address objects");
        }
        int processorCount = 0;
        if (value.getArithmeticProcessor() != null) processorCount++;
        if (value.getCategoryProcessor() != null) processorCount++;
        if (value.getLookupProcessor() != null) processorCount++;
        if (value.getGeoIpParserProcessor() != null) processorCount++;
        if (value.getAttributeReMapper() != null) processorCount++;
        if (value.getDateReMapper() != null) processorCount++;
        if (value.getGrokParser() != null) processorCount++;
        if (value.getMessageReMapper() != null) processorCount++;
        if (value.getNestedPipelineProcessor() != null) processorCount++;
        if (value.getServiceReMapper() != null) processorCount++;
        if (value.getStatusReMapper() != null) processorCount++;
        if (value.getStringBuilderProcessor() != null) processorCount++;
        if (value.getTraceIdReMapperProcessor() != null) processorCount++;
        if (value.getUrlParser() != null) processorCount++;
        if (value.getUserAgentParser() != null) processorCount++;

        if(processorCount == 0 || processorCount > 1)
            return false;
        else
            return true;
    }
}
