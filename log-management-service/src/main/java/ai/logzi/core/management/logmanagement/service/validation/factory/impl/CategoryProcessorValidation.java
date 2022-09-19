package ai.logzi.core.management.logmanagement.service.validation.factory.impl;

import ai.logzi.core.microservice.logmanagement.common.constant.I18Constant;
import ai.logzi.core.management.logmanagement.service.dto.log.LogPipelineProcessorDto;
import ai.logzi.core.management.logmanagement.service.mapper.LogPipelineProcessorDtoMapper;
import ai.logzi.core.management.logmanagement.service.validation.dto.FieldErrorDto;
import ai.logzi.core.management.logmanagement.service.validation.factory.LogPipelineProcessorValidation;
import ai.logzi.core.management.logmanagement.service.validation.util.LogPipelineValidationUtil;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CategoryProcessorValidation implements LogPipelineProcessorValidation {
    private final LogPipelineProcessorDtoMapper logPipelineProcessorDtoMapper;
    private final LogPipelineValidationUtil logPipelineValidationUtil;

    @Override
    public List<FieldErrorDto> validate(final LogPipelineProcessorDto logPipelineProcessorDto) {

        var allErrors = this.logPipelineValidationUtil
                .validateLogPipelineBaseProcessor(logPipelineProcessorDto,
                        logPipelineProcessorDto.getIndex());

        // Check Category Processor categories Not Empty
        if (logPipelineProcessorDto.getCategoryProcessor().getCategories() == null || logPipelineProcessorDto.getCategoryProcessor().getCategories().isEmpty()) {

            FieldErrorDto error = new FieldErrorDto("processors" + "[" + logPipelineProcessorDto.getIndex() + "].categories",
                    I18Constant.LOG_PROCESSOR_CATEGORY_PROCESSOR_CATEGORY_IS_EMPTY.getCode());
            allErrors.add(error);
        }

        if (logPipelineProcessorDto.getCategoryProcessor().getTarget() == null) {
            FieldErrorDto error = new FieldErrorDto("processors" + "[" + logPipelineProcessorDto.getIndex() + "].target",
                    I18Constant.LOG_PROCESSOR_TARGET_IS_EMPTY.getCode());
            allErrors.add(error);
        }

        return allErrors;

    }
}
