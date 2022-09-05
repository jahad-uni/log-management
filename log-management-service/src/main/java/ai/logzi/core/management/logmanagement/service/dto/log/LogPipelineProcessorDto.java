package ai.logzi.core.management.logmanagement.service.dto.log;

import ai.logzi.core.management.logmanagement.service.dto.log.processor.*;
import ai.logzi.core.management.logmanagement.service.validation.dto.FieldErrorDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogPipelineProcessorDto extends BaseProcessorDto {

    //Grok parser
    private GrokParserProcessorDto grokParser;

    //Date ReMapper
    private DateReMapperProcessorDto dateReMapper;

    //Status ReMapper
    private StatusReMapperProcessorDto statusReMapper;

    //Service ReMapper
    private ServiceReMapperProcessorDto serviceReMapper;

    //Message ReMapper
    private MessageReMapperProcessorDto messageReMapper;

    //Attribute ReMapper
    private AttributeReMapperProcessorDto attributeReMapper;

    //url Parser
    private UrlParserProcessorDto urlParser;

    private UserAgentParserProcessorDto userAgentParser;

    private CategoryProcessorDto categoryProcessor;

    private ArithmeticProcessorDto arithmeticProcessor;

    private StringBuilderProcessorDto stringBuilderProcessor;

    private GeoIpParserProcessorDto geoIpParserProcessor;

    private LookupProcessorDto lookupProcessor;

    private TraceIdReMapperProcessorDto traceIdReMapperProcessor;

    // Nested LogPipeline
    private LogPipelineDto nestedPipelineProcessor;


    //other use
    private List<FieldErrorDto> fieldErrorDtoList;
    private String index;

}
