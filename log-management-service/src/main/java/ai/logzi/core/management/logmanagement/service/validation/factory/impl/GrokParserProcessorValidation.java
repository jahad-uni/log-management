package ai.logzi.core.management.logmanagement.service.validation.factory.impl;


import ai.logzi.core.management.logmanagement.service.dto.log.LogPipelineProcessorDto;
import ai.logzi.core.management.logmanagement.service.mapper.LogPipelineProcessorDtoMapper;
import ai.logzi.core.management.logmanagement.service.validation.factory.LogPipelineProcessorValidation;
import ai.logzi.core.management.logmanagement.service.validation.util.LogPipelineValidationUtil;
import ai.logzi.core.management.logmanagement.service.constant.I18Constant;
import ai.logzi.core.management.logmanagement.service.validation.dto.FieldErrorDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GrokParserProcessorValidation implements LogPipelineProcessorValidation {

    private final LogPipelineProcessorDtoMapper logPipelineProcessorDtoMapper;
    private final LogPipelineValidationUtil logPipelineValidationUtil;

    @Override
    public List<FieldErrorDto> validate(final LogPipelineProcessorDto logPipelineProcessorDto) {

//        var grokParserProcessorDto = this
//                .logPipelineProcessorDtoMapper
//                .toGrokParser(logPipelineProcessorDto);


        var allErrors = this
                .logPipelineValidationUtil
                .validateLogPipelineBaseProcessor(logPipelineProcessorDto,
                        logPipelineProcessorDto.getIndex());

        // Check GrokParser Processor grok.match_rules Not Empty
        if (logPipelineProcessorDto.getGrokParser().getGrok() == null ||
                logPipelineProcessorDto.getGrokParser().getGrok().getMatch_rules() == null ||
                logPipelineProcessorDto.getGrokParser().getGrok().getMatch_rules().isEmpty()) {
            FieldErrorDto error = new FieldErrorDto(
                    "processors[" +
                            logPipelineProcessorDto.getIndex() +
                            "].grok.match_rules",
                    I18Constant.LOG_PROCESSOR_GROK_MATCH_RULE_IS_EMPTY.getCode());
            allErrors.add(error);
        }
        if (logPipelineProcessorDto.getGrokParser().getSource() == null || logPipelineProcessorDto.getGrokParser().getSource().isEmpty()) {

            FieldErrorDto error = new FieldErrorDto("processors" + "[" + logPipelineProcessorDto.getIndex() + "].source",
                    I18Constant.LOG_PROCESSOR_SOURCE_IS_EMPTY.getCode());
            allErrors.add(error);
        }

        return allErrors;
    }
}
