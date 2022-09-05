package ai.logzi.core.management.logmanagement.service.validation.factory.impl;

import ai.logzi.core.management.logmanagement.service.dto.log.LogPipelineProcessorDto;
import ai.logzi.core.management.logmanagement.service.validation.factory.LogPipelineProcessorValidation;
import ai.logzi.core.management.logmanagement.service.constant.I18Constant;
import ai.logzi.core.management.logmanagement.service.validation.dto.FieldErrorDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class UnknownProcessorValidation implements LogPipelineProcessorValidation {

    @Override
    public LogPipelineProcessorDto validate(final LogPipelineProcessorDto logPipelineProcessorDto) {

        var allErrors = new ArrayList<FieldErrorDto>();
        // Check Processor type is valid
        if (logPipelineProcessorDto.getType() == null ||
                logPipelineProcessorDto.getType().isEmpty()) {
            var unknownError = new FieldErrorDto("processors[" + logPipelineProcessorDto.getIndex() + "].type",
                    I18Constant.LOG_PROCESSOR_TYPE_IS_EMPTY.getCode());
            allErrors.add(unknownError);
        } else {
            var unknownError = new FieldErrorDto("processors[" + logPipelineProcessorDto.getIndex() + "].type",
                    I18Constant.LOG_PROCESSOR_TYPE_IS_UNKNOWN.getCode());
            allErrors.add(unknownError);
        }
        var out = new LogPipelineProcessorDto();
        out.setFieldErrorDtoList(allErrors);
        return out;
    }
}
