package ai.logzi.core.management.logmanagement.service.validation.factory.impl;

import ai.logzi.core.management.logmanagement.service.constant.I18Constant;
import ai.logzi.core.management.logmanagement.service.dto.log.LogPipelineProcessorDto;
import ai.logzi.core.management.logmanagement.service.mapper.LogPipelineProcessorDtoMapper;
import ai.logzi.core.management.logmanagement.service.validation.dto.FieldErrorDto;
import ai.logzi.core.management.logmanagement.service.validation.factory.LogPipelineProcessorValidation;
import ai.logzi.core.management.logmanagement.service.validation.util.LogPipelineValidationUtil;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StringBuilderProcessorValidation implements LogPipelineProcessorValidation {
    private final LogPipelineProcessorDtoMapper logPipelineProcessorDtoMapper;
    private final LogPipelineValidationUtil logPipelineValidationUtil;

    @Override
    public LogPipelineProcessorDto validate(final LogPipelineProcessorDto logPipelineProcessorDto) {


        var allErrors = this.logPipelineValidationUtil
                .validateLogPipelineBaseProcessor(logPipelineProcessorDto,
                        logPipelineProcessorDto.getIndex());

        // Check StringBuilder Processor template Not Empty
        if (logPipelineProcessorDto.getStringBuilderProcessor().getTemplate() == null ||
                logPipelineProcessorDto.getStringBuilderProcessor().getTemplate().isEmpty()) {

            FieldErrorDto error = new FieldErrorDto("processors" + "[" + logPipelineProcessorDto.getIndex() + "].template",
                    I18Constant.LOG_PROCESSOR_STRING_BUILDER_PROCESSOR_TEMPLATE_IS_EMPTY.getCode());
            allErrors.add(error);
        }

        if (logPipelineProcessorDto.getStringBuilderProcessor().getTarget() == null) {
            FieldErrorDto error = new FieldErrorDto("processors" + "[" + logPipelineProcessorDto.getIndex() + "].target",
                    I18Constant.LOG_PROCESSOR_TARGET_IS_EMPTY.getCode());
            allErrors.add(error);
        }


        logPipelineProcessorDto.setFieldErrorDtoList(allErrors);
        return logPipelineProcessorDto;

    }
}
