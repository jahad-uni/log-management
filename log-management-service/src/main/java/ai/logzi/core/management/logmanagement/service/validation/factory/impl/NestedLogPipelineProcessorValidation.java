package ai.logzi.core.management.logmanagement.service.validation.factory.impl;


import ai.logzi.core.management.logmanagement.service.exception.LogPipelineException;
import ai.logzi.core.management.logmanagement.service.dto.log.LogPipelineProcessorDto;
import ai.logzi.core.management.logmanagement.service.mapper.LogPipelineProcessorDtoMapper;
import ai.logzi.core.management.logmanagement.service.validation.factory.LogPipelineProcessorValidation;
import ai.logzi.core.management.logmanagement.service.validation.factory.LogPipelineProcessorValidationFactory;
import ai.logzi.core.management.logmanagement.service.validation.util.LogPipelineValidationUtil;
import ai.logzi.core.management.logmanagement.service.constant.I18Constant;
import ai.logzi.core.management.logmanagement.service.validation.dto.FieldErrorDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class NestedLogPipelineProcessorValidation implements LogPipelineProcessorValidation {

    private final LogPipelineProcessorValidationFactory logPipelineProcessorValidationFactory;
    private final LogPipelineProcessorDtoMapper logPipelineProcessorDtoMapper;
    private final LogPipelineValidationUtil logPipelineValidationUtil;

    @Override
    public LogPipelineProcessorDto validate(final LogPipelineProcessorDto logPipelineProcessorDto) throws Exception {

        var nestLogPipelineProcessorDto = this.logPipelineProcessorDtoMapper
                .toNestedLogPipeline(logPipelineProcessorDto);

        var allErrors = this.logPipelineValidationUtil
                .validateLogPipelineBaseProcessor(nestLogPipelineProcessorDto,
                        logPipelineProcessorDto.getIndex());

        var pipelineErrors = this.logPipelineValidationUtil
                .validateLogPipeline(nestLogPipelineProcessorDto.getPipeline());

        for (var fieldError : pipelineErrors) {
            allErrors.add(new FieldErrorDto(
                    "processors[" + logPipelineProcessorDto.getIndex() + "]." + "pipeline." + fieldError.getField(),
                    fieldError.getError()
            ));
        }
        var validatedLogPipelineProcessorList = new ArrayList<LogPipelineProcessorDto>();
        if (nestLogPipelineProcessorDto.getPipeline() != null) {
            var processors = logPipelineProcessorDto
                    .getNestedPipelineProcessor()
                    .getProcessors();
            for (var processorDto : processors) {
                processorDto.setIndex( String.valueOf(processors.indexOf(processorDto)));
                // Check nested pipeline does not have processor of type nested-log-pipeline.
                if (processorDto.getNestedPipelineProcessor() != null)
                    throw new LogPipelineException(I18Constant.LOG_PROCESSOR_PIPELINE_NESTED_PIPELINE_NOT_VALID.getCode());
//                "you can not have nested pipeline in another nested pipeline"
                // Check Nested Pipeline Processors Validations
                var validatedLogPipelineProcessor = logPipelineProcessorValidationFactory
                        .create(processorDto)
                        .validate(processorDto);
                validatedLogPipelineProcessorList.add(validatedLogPipelineProcessor);
                // Generate Correct field key
                for (var fieldError : validatedLogPipelineProcessor.getFieldErrorDtoList()) {
                    allErrors.add(new FieldErrorDto(
                            "processors[" + logPipelineProcessorDto.getIndex() + "]." + "pipeline." + fieldError.getField(),
                            fieldError.getError()
                    ));
                }
            }
        }
        nestLogPipelineProcessorDto.getPipeline().setProcessors(validatedLogPipelineProcessorList);

        var out = this.logPipelineProcessorDtoMapper
                .fromNestedLogPipeline(nestLogPipelineProcessorDto);
        out.setFieldErrorDtoList(allErrors);
        return out;
    }
}
