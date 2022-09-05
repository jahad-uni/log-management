package ai.logzi.core.management.logmanagement.service.mapper;

import ai.logzi.core.management.logmanagement.service.dto.log.LogPipelineProcessorDto;
import ai.logzi.core.management.logmanagement.service.dto.log.processor.*;
import org.mapstruct.Mapper;

@Mapper
public interface LogPipelineProcessorDtoMapper {

    GrokParserProcessorDto toGrokParser(LogPipelineProcessorDto logPipelineProcessorDto);

    LogPipelineProcessorDto fromGrokParser(GrokParserProcessorDto grokParserProcessorDto);

    DateReMapperProcessorDto toDateRemapper(LogPipelineProcessorDto logPipelineProcessorDto);

    LogPipelineProcessorDto fromDateRemapper(DateReMapperProcessorDto dateRemapperProcessorDto);

    NestedLogPipelineProcessorDto toNestedLogPipeline(LogPipelineProcessorDto logPipelineProcessorDto);

    LogPipelineProcessorDto fromNestedLogPipeline(NestedLogPipelineProcessorDto nestedLogPipelineProcessorDto);

    StatusReMapperProcessorDto toStatusReMapper(LogPipelineProcessorDto logPipelineProcessorDto);

    LogPipelineProcessorDto fromStatusReMapper(StatusReMapperProcessorDto statusReMapperProcessorDto);

    ServiceReMapperProcessorDto toServiceReMapper(LogPipelineProcessorDto logPipelineProcessorDto);

    LogPipelineProcessorDto fromServiceReMapper(ServiceReMapperProcessorDto serviceReMapperPipelineProcessorDto);

    MessageReMapperProcessorDto toMessageReMapper(LogPipelineProcessorDto logPipelineProcessorDto);

    LogPipelineProcessorDto fromMessageReMapper(MessageReMapperProcessorDto messageReMapperProcessorDto);

    AttributeReMapperProcessorDto toAttributeReMapper(LogPipelineProcessorDto logPipelineProcessorDto);

    LogPipelineProcessorDto fromAttributeReMapper(AttributeReMapperProcessorDto attributeReMapperProcessorDto);

    UrlParserProcessorDto toUrlParser(LogPipelineProcessorDto logPipelineProcessorDto);

    LogPipelineProcessorDto fromUrlParser(UrlParserProcessorDto urlParserProcessorDto);

    UserAgentParserProcessorDto toUserAgentParser(LogPipelineProcessorDto logPipelineProcessorDto);

    LogPipelineProcessorDto fromUserAgentParser(UserAgentParserProcessorDto userAgentParserProcessorDto);

    ArithmeticProcessorDto toArithmeticProcessor(LogPipelineProcessorDto logPipelineProcessorDto);

    LogPipelineProcessorDto fromArithmeticProcessor(ArithmeticProcessorDto arithmeticProcessorDto);

    StringBuilderProcessorDto toStringBuilderProcessor(LogPipelineProcessorDto logPipelineProcessorDto);

    LogPipelineProcessorDto fromStringBuilderProcessor(StringBuilderProcessorDto stringBuilderProcessorDto);

    GeoIpParserProcessorDto toGeoIpParser(LogPipelineProcessorDto logPipelineProcessorDto);

    LogPipelineProcessorDto fromGeoIpParser(GeoIpParserProcessorDto geoIpParserProcessorDto);

    TraceIdReMapperProcessorDto toTraceIdReMapper(LogPipelineProcessorDto logPipelineProcessorDto);

    LogPipelineProcessorDto fromTraceIdReMapper(TraceIdReMapperProcessorDto traceIdReMapperProcessorDto);

    CategoryProcessorDto toCategoryProcessor(LogPipelineProcessorDto logPipelineProcessorDto);

    LogPipelineProcessorDto fromCategoryProcessor(CategoryProcessorDto categoryProcessorDto);

    LookupProcessorDto toLookupProcessor(LogPipelineProcessorDto logPipelineProcessorDto);

    LogPipelineProcessorDto fromLookupProcessor(LookupProcessorDto lookupProcessorDto);

}
