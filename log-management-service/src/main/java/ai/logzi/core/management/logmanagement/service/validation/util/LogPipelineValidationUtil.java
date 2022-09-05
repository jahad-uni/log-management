package ai.logzi.core.management.logmanagement.service.validation.util;

import ai.logzi.core.management.logmanagement.service.dto.log.LogPipelineDto;
import ai.logzi.core.management.logmanagement.service.dto.log.processor.BaseProcessorDto;
import ai.logzi.core.management.logmanagement.service.constant.I18Constant;
import ai.logzi.core.management.logmanagement.service.validation.dto.FieldErrorDto;
import ai.logzi.core.management.logmanagement.service.type.log.LogPipelineType;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LogPipelineValidationUtil {

    public List<FieldErrorDto> validateLogPipelineBaseProcessor(final BaseProcessorDto baseProcessorDto,
                                                                final String index) {

        var fieldErrorDtos = new ArrayList<FieldErrorDto>();
        // Check  Processor name Not Empty
        if (baseProcessorDto.getName() == null || baseProcessorDto.getName().isEmpty()) {
            FieldErrorDto error = new FieldErrorDto("processors[" + index + "].name",
                    I18Constant.LOG_PROCESSOR_NAME_IS_EMPTY.getCode());
            fieldErrorDtos.add(error);
        }

        return fieldErrorDtos;
    }


    public List<FieldErrorDto> validateLogPipeline(final LogPipelineDto logPipelineDto) {

        var fieldErrorDtos = new ArrayList<FieldErrorDto>();
        // Check LogPipeline name Not Empty
        if (logPipelineDto.getName() == null || logPipelineDto.getName().isEmpty()) {
            var error = new FieldErrorDto("name",
                    I18Constant.LOG_PIPELINE_NAME_IS_EMPTY.getCode());
            fieldErrorDtos.add(error);
        }
        if (logPipelineDto.getType() == null || logPipelineDto.getType().isEmpty()) {
            var error = new FieldErrorDto("type",
                    I18Constant.LOG_PIPELINE_TYPE_IS_EMPTY.getCode());
            fieldErrorDtos.add(error);
        }
        if (logPipelineDto.getType() != null &&
                !logPipelineDto.getType().equals(LogPipelineType.CUSTOM.getCode()) &&
                !logPipelineDto.getType().equals(LogPipelineType.INTEGRATION.getCode())) {
            var error = new FieldErrorDto("type",
                    I18Constant.LOG_PIPELINE_TYPE_IS_UNKNOWN.getCode());
            fieldErrorDtos.add(error);
        }
        return fieldErrorDtos;
    }

}
