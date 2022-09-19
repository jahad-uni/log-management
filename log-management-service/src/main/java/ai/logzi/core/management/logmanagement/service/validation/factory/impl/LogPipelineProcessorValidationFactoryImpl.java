package ai.logzi.core.management.logmanagement.service.validation.factory.impl;

import ai.logzi.core.management.logmanagement.service.dto.log.LogPipelineProcessorDto;
import ai.logzi.core.management.logmanagement.service.mapper.LogPipelineProcessorDtoMapper;
import ai.logzi.core.management.logmanagement.service.validation.factory.LogPipelineProcessorValidation;
import ai.logzi.core.management.logmanagement.service.validation.factory.LogPipelineProcessorValidationFactory;
import ai.logzi.core.management.logmanagement.service.validation.util.LogPipelineValidationUtil;
import ai.logzi.core.management.logmanagement.service.type.log.LogPipelineProcessorType;
import ai.logzi.core.microservice.logmanagement.common.constant.I18Constant;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LogPipelineProcessorValidationFactoryImpl implements LogPipelineProcessorValidationFactory {

    private final LogPipelineProcessorDtoMapper logPipelineProcessorDtoMapper;
    private final LogPipelineValidationUtil logPipelineValidationUtil;

    @Override
    public LogPipelineProcessorValidation create(final LogPipelineProcessorDto logPipelineProcessorDto) throws Exception {

        LogPipelineProcessorValidation validation = null;
        int processorCount = 0;
        if (logPipelineProcessorDto.getType() == null ||
                logPipelineProcessorDto.getType().isEmpty()) {
            validation = new UnknownProcessorValidation();
        } else if (logPipelineProcessorDto.getType()
                .equals(LogPipelineProcessorType.GROK_PARSER.getCode())) {
            validation = new GrokParserProcessorValidation(
                    logPipelineProcessorDtoMapper,
                    logPipelineValidationUtil);
        } else if (logPipelineProcessorDto.getType()
                .equals(LogPipelineProcessorType.DATE_REMAPPER.getCode())) {
            validation = new DateRemapperProcessorValidation(
                    logPipelineProcessorDtoMapper,
                    logPipelineValidationUtil);
        } else if (logPipelineProcessorDto.getType()
                .equals(LogPipelineProcessorType.PIPELINE.getCode())) {
            validation = new NestedLogPipelineProcessorValidation(
                    this,
                    logPipelineProcessorDtoMapper,
                    logPipelineValidationUtil);
        } else if (logPipelineProcessorDto.getType()
                .equals(LogPipelineProcessorType.STATUS_REMAPPER.getCode())) {
            validation = new StatusReMapperProcessorValidation(
                    logPipelineProcessorDtoMapper,
                    logPipelineValidationUtil);
        } else if (logPipelineProcessorDto.getType()
                .equals(LogPipelineProcessorType.SERVICE_REMAPPER.getCode())) {
            validation = new ServiceReMapperProcessorValidation(
                    logPipelineProcessorDtoMapper,
                    logPipelineValidationUtil);
        } else if (logPipelineProcessorDto.getType()
                .equals(LogPipelineProcessorType.MESSAGE_REMAPPER.getCode())) {
            validation = new MessageReMapperProcessorValidation(
                    logPipelineProcessorDtoMapper,
                    logPipelineValidationUtil);
        } else if (logPipelineProcessorDto.getType()
                .equals(LogPipelineProcessorType.ATTRIBUTE_REMAPPER.getCode())) {
            validation = new AttributeReMapperProcessorValidation(
                    logPipelineProcessorDtoMapper,
                    logPipelineValidationUtil);
        } else if (logPipelineProcessorDto.getType()
                .equals(LogPipelineProcessorType.URL_PARSER.getCode())) {
            validation = new UrlParserProcessorValidation(
                    logPipelineProcessorDtoMapper,
                    logPipelineValidationUtil);
        } else if (logPipelineProcessorDto.getType()
                .equals(LogPipelineProcessorType.USER_AGENT_PARSER.getCode())) {
            validation = new UserAgentParserProcessorValidation(
                    logPipelineProcessorDtoMapper,
                    logPipelineValidationUtil);
        } else if (logPipelineProcessorDto.getType()
                .equals(LogPipelineProcessorType.ARITHMETIC_PROCESSOR.getCode())) {
            validation = new ArithmeticProcessorValidation(
                    logPipelineProcessorDtoMapper,
                    logPipelineValidationUtil);
        } else if (logPipelineProcessorDto.getType()
                .equals(LogPipelineProcessorType.STRING_BUILDER_PROCESSOR.getCode())) {
            validation = new StringBuilderProcessorValidation(
                    logPipelineProcessorDtoMapper,
                    logPipelineValidationUtil);
        } else if (logPipelineProcessorDto.getType()
                .equals(LogPipelineProcessorType.GEO_IP_PARSER.getCode())) {
            validation = new GeoIpParserProcessorValidation(
                    logPipelineProcessorDtoMapper,
                    logPipelineValidationUtil);
        } else if (logPipelineProcessorDto.getType()
                .equals(LogPipelineProcessorType.TRACE_ID_REMAPPER.getCode())) {
            validation = new TraceIdReMapperValidation(
                    logPipelineProcessorDtoMapper,
                    logPipelineValidationUtil);
        } else if (logPipelineProcessorDto.getType()
                .equals(LogPipelineProcessorType.CATEGORY_PROCESSOR.getCode())) {
            validation = new CategoryProcessorValidation(
                    logPipelineProcessorDtoMapper,
                    logPipelineValidationUtil);
        } else if (logPipelineProcessorDto.getType()
                .equals(LogPipelineProcessorType.LOOKUP_PROCESSOR.getCode())) {
            validation = new LookupProcessorValidation(
                    logPipelineProcessorDtoMapper,
                    logPipelineValidationUtil);
        } else validation = new UnknownProcessorValidation();

//        if(processorCount == 0) {
//            throw new Exception(I18Constant.LOG_PROCESSOR_COUNT_IS_INVALID.getCode());
//        }
//
//        if(processorCount >1){
//            throw new Exception(I18Constant.LOG_PROCESSOR_COUNT_IS_INVALID.getCode());
//        }

        return validation;

    }
}
