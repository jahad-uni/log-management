package ai.logzi.core.microservice.logmanagement.entity.log;


import ai.logzi.core.microservice.logmanagement.entity.log.processor.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogPipelineProcessorEntity {

    private String id;
    // Static Fields
    private String name;
    private boolean is_enabled;
    private String type;


    private GrokParserProcessorEntity grokParser;

    private DateReMapperProcessorEntity dateReMapper;

    private StatusReMapperProcessorEntity statusReMapper;

    private ServiceReMapperProcessorEntity serviceReMapper;

    private MessageReMapperProcessorEntity messageReMapper;

    private AttributeReMapperProcessorEntity attributeReMapper;

    private UrlParserProcessorEntity urlParser;

    private UserAgentParserProcessorEntity userAgentParser;

    private CategoryProcessorEntity categoryProcessor;

    private ArithmeticProcessorEntity arithmeticProcessor;

    private StringBuilderProcessorEntity stringBuilderProcessor;

    private GeoIpParserProcessorEntity geoIpParserProcessor;

    private LogPipelineEntity nestedPipelineProcessor;

    private LookupProcessorEntity lookupProcessor;

    private TraceIdReMapperProcessorEntity traceIdReMapperProcessor;


}
