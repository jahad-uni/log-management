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
public class LookupProcessorValidation implements LogPipelineProcessorValidation {
    private final LogPipelineProcessorDtoMapper logPipelineProcessorDtoMapper;
    private final LogPipelineValidationUtil logPipelineValidationUtil;

    @Override
    public List<FieldErrorDto> validate(final LogPipelineProcessorDto logPipelineProcessorDto) {


        var allErrors = this.logPipelineValidationUtil
                .validateLogPipelineBaseProcessor(logPipelineProcessorDto,
                        logPipelineProcessorDto.getIndex());

        // Check lookup Processor source Not Empty
        if (logPipelineProcessorDto.getLookupProcessor().getSource() == null ||
                logPipelineProcessorDto.getLookupProcessor().getSource().isEmpty()) {

            FieldErrorDto error = new FieldErrorDto("processors" + "[" + logPipelineProcessorDto.getIndex() + "].source",
                    I18Constant.LOG_PROCESSOR_SOURCE_IS_EMPTY.getCode());
            allErrors.add(error);
        }
        // Check lookup Processor target Not Empty
        if (logPipelineProcessorDto.getLookupProcessor().getTarget() == null ||
                logPipelineProcessorDto.getLookupProcessor().getTarget().isEmpty()) {
            FieldErrorDto error = new FieldErrorDto("processors" + "[" + logPipelineProcessorDto.getIndex() + "].target",
                    I18Constant.LOG_PROCESSOR_TARGET_IS_EMPTY.getCode());
            allErrors.add(error);
        }

        // Check lookup Processor look_up_table Not Empty
        if (logPipelineProcessorDto.getLookupProcessor().getLookup_table()== null || logPipelineProcessorDto.getLookupProcessor().getLookup_table().isEmpty()) {
            FieldErrorDto error = new FieldErrorDto("processors" + "[" + logPipelineProcessorDto.getIndex() + "].Look_up_table",
                    I18Constant.LOG_PROCESSOR_LOOKUP_PROCESSOR_LOOKUP_TABLE_IS_EMPTY.getCode());
            allErrors.add(error);
        }

        return allErrors;

    }
}
