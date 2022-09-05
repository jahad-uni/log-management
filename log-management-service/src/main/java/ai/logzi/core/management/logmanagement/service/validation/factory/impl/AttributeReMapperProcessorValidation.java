package ai.logzi.core.management.logmanagement.service.validation.factory.impl;

import ai.logzi.core.management.logmanagement.service.dto.log.LogPipelineProcessorDto;
import ai.logzi.core.management.logmanagement.service.mapper.LogPipelineProcessorDtoMapper;
import ai.logzi.core.management.logmanagement.service.validation.factory.LogPipelineProcessorValidation;
import ai.logzi.core.management.logmanagement.service.validation.util.LogPipelineValidationUtil;
import ai.logzi.core.management.logmanagement.service.constant.I18Constant;
import ai.logzi.core.management.logmanagement.service.validation.dto.FieldErrorDto;
import ai.logzi.core.management.logmanagement.service.type.log.AttributeReMapperSourceType;
import ai.logzi.core.management.logmanagement.service.type.log.AttributeReMapperTargetFormat;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AttributeReMapperProcessorValidation implements LogPipelineProcessorValidation {
    private final LogPipelineProcessorDtoMapper logPipelineProcessorDtoMapper;
    private final LogPipelineValidationUtil logPipelineValidationUtil;


    @Override
    public LogPipelineProcessorDto validate(LogPipelineProcessorDto logPipelineProcessorDto) {


        var allErrors = this
                .logPipelineValidationUtil
                .validateLogPipelineBaseProcessor(logPipelineProcessorDto,
                        logPipelineProcessorDto.getIndex());

        // Check AttributeReMapper Processor sources Not Empty
        if (logPipelineProcessorDto.getAttributeReMapper().getSources() == null ||
                logPipelineProcessorDto.getAttributeReMapper().getSources().isEmpty()) {

            FieldErrorDto error = new FieldErrorDto("processors" + "[" + logPipelineProcessorDto.getIndex() + "].sources",
                    I18Constant.LOG_PROCESSOR_SOURCE_IS_EMPTY.getCode());
            allErrors.add(error);
        }


        // Check
        if (logPipelineProcessorDto.getAttributeReMapper().getSourceType() == null) {
            FieldErrorDto error = new FieldErrorDto(
                    "processors[" +
                            logPipelineProcessorDto.getIndex() +
                            "].sourceType",
                    I18Constant.LOG_PROCESSOR_ATTRIBUTE_REMAPPER_SOURCE_TYPE_IS_EMPTY.getCode());
            allErrors.add(error);
        } else if (!(logPipelineProcessorDto.getAttributeReMapper().getSourceType()
                .equals(AttributeReMapperSourceType.ATTRIBUTE.getCode()) ||
                logPipelineProcessorDto.getAttributeReMapper().getSourceType()
                        .equals(AttributeReMapperSourceType.TAG.getCode()))) {
            FieldErrorDto error = new FieldErrorDto(
                    "processors[" +
                            logPipelineProcessorDto.getIndex() +
                            "].sourceType",
                    I18Constant.LOG_PROCESSOR_ATTRIBUTE_REMAPPER_SOURCE_TYPE_IS_UNKNOWN.getCode());
            allErrors.add(error);
        }

        if (logPipelineProcessorDto.getAttributeReMapper().getTargetFormat() == null) {
            FieldErrorDto error = new FieldErrorDto(
                    "processors[" +
                            logPipelineProcessorDto.getIndex() +
                            "].targetFormat",
                    I18Constant.LOG_PROCESSOR_ATTRIBUTE_REMAPPER_TARGET_FORMAT_IS_EMPTY.getCode());
            allErrors.add(error);

        } else if (!(logPipelineProcessorDto.getAttributeReMapper().getTargetFormat()
                .equals(AttributeReMapperTargetFormat.AUTO.getCode())
                || logPipelineProcessorDto.getAttributeReMapper().getTargetFormat()
                .equals(AttributeReMapperTargetFormat.DOUBLE.getCode())
                || logPipelineProcessorDto.getAttributeReMapper().getTargetFormat()
                .equals(AttributeReMapperTargetFormat.INTEGER.getCode())
                || logPipelineProcessorDto.getAttributeReMapper().getTargetFormat()
                .equals(AttributeReMapperTargetFormat.STRING.getCode())
        )) {
            FieldErrorDto error = new FieldErrorDto(
                    "processors[" +
                            logPipelineProcessorDto.getIndex() +
                            "].targetFormat",
                    I18Constant.LOG_PROCESSOR_ATTRIBUTE_REMAPPER_TARGET_FORMAT_IS_UNKNOWN.getCode());
            allErrors.add(error);
        }


        if (logPipelineProcessorDto.getAttributeReMapper().getTargetType() == null) {
            FieldErrorDto error = new FieldErrorDto(
                    "processors[" +
                            logPipelineProcessorDto.getIndex() +
                            "].targetType",
                    I18Constant.LOG_PROCESSOR_ATTRIBUTE_REMAPPER_TARGET_TYPE_IS_EMPTY.getCode());
            allErrors.add(error);
        } else if (!(logPipelineProcessorDto.getAttributeReMapper().getTargetType()
                .equals(AttributeReMapperSourceType.ATTRIBUTE.getCode()) ||
                logPipelineProcessorDto.getAttributeReMapper().getTargetType()
                        .equals(AttributeReMapperSourceType.TAG.getCode()))) {
            FieldErrorDto error = new FieldErrorDto(
                    "processors[" +
                            logPipelineProcessorDto.getIndex() +
                            "].targetType",
                    I18Constant.LOG_PROCESSOR_ATTRIBUTE_REMAPPER_TARGET_TYPE_IS_UNKNOWN.getCode());
            allErrors.add(error);
        } else if (logPipelineProcessorDto.getAttributeReMapper().getTarget() == null) {
            FieldErrorDto error = new FieldErrorDto(
                    "processors[" +
                            logPipelineProcessorDto.getIndex() +
                            "].target",
                    I18Constant.LOG_PROCESSOR_TARGET_IS_EMPTY.getCode());
            allErrors.add(error);
        }

        logPipelineProcessorDto.setFieldErrorDtoList(allErrors);

        return logPipelineProcessorDto;
    }
}
