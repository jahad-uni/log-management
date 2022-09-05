package ai.logzi.core.management.logmanagement.service.validation.factory.impl;

import ai.logzi.core.management.logmanagement.service.dto.log.LogPipelineProcessorDto;
import ai.logzi.core.management.logmanagement.service.mapper.LogPipelineProcessorDtoMapper;
import ai.logzi.core.management.logmanagement.service.validation.factory.LogPipelineProcessorValidation;
import ai.logzi.core.management.logmanagement.service.validation.factory.LogPipelineProcessorValidationFactory;
import ai.logzi.core.management.logmanagement.service.validation.util.LogPipelineValidationUtil;
import ai.logzi.core.management.logmanagement.service.type.log.LogPipelineProcessorType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LogPipelineProcessorValidationFactoryImpl implements LogPipelineProcessorValidationFactory {

    private final LogPipelineProcessorDtoMapper logPipelineProcessorDtoMapper;
    private final LogPipelineValidationUtil logPipelineValidationUtil;

    @Override
    public LogPipelineProcessorValidation create(final LogPipelineProcessorDto logPipelineProcessorDto) throws Exception {

        if (logPipelineProcessorDto.getType() == null ||
                logPipelineProcessorDto.getType().isEmpty())
            return new UnknownProcessorValidation();
        else if (logPipelineProcessorDto.getType()
                .equals(LogPipelineProcessorType.GROK_PARSER.getCode()))
            return new GrokParserProcessorValidation(
                    logPipelineProcessorDtoMapper,
                    logPipelineValidationUtil);
        else if (logPipelineProcessorDto.getType()
                .equals(LogPipelineProcessorType.DATE_REMAPPER.getCode()))
            return new DateRemapperProcessorValidation(
                    logPipelineProcessorDtoMapper,
                    logPipelineValidationUtil);
        else if (logPipelineProcessorDto.getType()
                .equals(LogPipelineProcessorType.PIPELINE.getCode()))
            return new NestedLogPipelineProcessorValidation(
                    this,
                    logPipelineProcessorDtoMapper,
                    logPipelineValidationUtil);
        else if (logPipelineProcessorDto.getType()
                .equals(LogPipelineProcessorType.STATUS_REMAPPER.getCode()))
            return new StatusReMapperProcessorValidation(
                    logPipelineProcessorDtoMapper,
                    logPipelineValidationUtil);
        else if (logPipelineProcessorDto.getType()
                .equals(LogPipelineProcessorType.SERVICE_REMAPPER.getCode()))
            return new ServiceReMapperProcessorValidation(
                    logPipelineProcessorDtoMapper,
                    logPipelineValidationUtil);
        else if (logPipelineProcessorDto.getType()
                .equals(LogPipelineProcessorType.MESSAGE_REMAPPER.getCode()))
            return new MessageReMapperProcessorValidation(
                    logPipelineProcessorDtoMapper,
                    logPipelineValidationUtil);
        else if (logPipelineProcessorDto.getType()
                .equals(LogPipelineProcessorType.ATTRIBUTE_REMAPPER.getCode()))
            return new AttributeReMapperProcessorValidation(
                    logPipelineProcessorDtoMapper,
                    logPipelineValidationUtil
            );
        else if (logPipelineProcessorDto.getType()
                .equals(LogPipelineProcessorType.URL_PARSER.getCode()))
            return new UrlParserProcessorValidation(
                    logPipelineProcessorDtoMapper,
                    logPipelineValidationUtil
            );
        else if (logPipelineProcessorDto.getType()
                .equals(LogPipelineProcessorType.USER_AGENT_PARSER.getCode()))
            return new UserAgentParserProcessorValidation(
                    logPipelineProcessorDtoMapper,
                    logPipelineValidationUtil
            );
        else if (logPipelineProcessorDto.getType()
                .equals(LogPipelineProcessorType.ARITHMETIC_PROCESSOR.getCode()))
            return new ArithmeticProcessorValidation(
                    logPipelineProcessorDtoMapper,
                    logPipelineValidationUtil
            );
        else if (logPipelineProcessorDto.getType()
                .equals(LogPipelineProcessorType.STRING_BUILDER_PROCESSOR.getCode()))
            return new StringBuilderProcessorValidation(
                    logPipelineProcessorDtoMapper,
                    logPipelineValidationUtil
            );
        else if (logPipelineProcessorDto.getType()
                .equals(LogPipelineProcessorType.GEO_IP_PARSER.getCode()))
            return new GeoIpParserProcessorValidation(
                    logPipelineProcessorDtoMapper,
                    logPipelineValidationUtil
            );
        else if (logPipelineProcessorDto.getType()
                .equals(LogPipelineProcessorType.TRACE_ID_REMAPPER.getCode()))
            return new TraceIdReMapperValidation(
                    logPipelineProcessorDtoMapper,
                    logPipelineValidationUtil
            );
        else if (logPipelineProcessorDto.getType()
                .equals(LogPipelineProcessorType.CATEGORY_PROCESSOR.getCode()))
            return new CategoryProcessorValidation(
                    logPipelineProcessorDtoMapper,
                    logPipelineValidationUtil
            );
        else if (logPipelineProcessorDto.getType()
                .equals(LogPipelineProcessorType.LOOKUP_PROCESSOR.getCode()))
            return new LookupProcessorValidation(
                    logPipelineProcessorDtoMapper,
                    logPipelineValidationUtil
            );

        else return new UnknownProcessorValidation();

    }
}
