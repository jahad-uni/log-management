package ai.logzi.core.microservice.logmanagement.api.model.log;

import ai.logzi.core.microservice.logmanagement.api.model.log.processors.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LogPipelineProcessor {

    private String id;
    // Static Fields
    private String name;
    private String type;
    @JsonProperty("is_enabled")
    private boolean is_enabled;

    //Grok Parser
    @JsonProperty("grok-parser")
    private GrokParserProcessor grokParser;

    //Date ReMapper
    @JsonProperty("date-remapper")
    private DateReMapperProcessor dateReMapper;

    //Status ReMapper
    @JsonProperty("status-remapper")
    private StatusReMapperProcessor statusReMapper;

    //Service ReMapper
    @JsonProperty("service-remapper")
    private ServiceReMapperProcessor serviceReMapper;

    //Message ReMapper
    @JsonProperty("message-remapper")
    private MessageReMapperProcessor messageReMapper;

    @JsonProperty("attribute-remapper")
    private AttributeReMapperProcessor attributeReMapper;

    //UrlParser
    @JsonProperty("url-parser")
    private UrlParserProcessor urlParser;

    @JsonProperty("user-agent-parser")
    private UserAgentParserProcessor userAgentParser;

    @JsonProperty("category-processor")
    private CategoryProcessor categoryProcessor;

    @JsonProperty("arithmetic-processor")
    private ArithmeticProcessor arithmeticProcessor;

    @JsonProperty("string-builder-processor")
    private StringBuilderProcessor stringBuilderProcessor;

    @JsonProperty("geo-ip-parser")
    private GeoIpParserProcessor geoIpParserProcessor;

    @JsonProperty("lookup-processor")
    private LookupProcessor lookupProcessor;

    @JsonProperty("trace-id-remapper")
    private TraceIdReMapperProcessor traceIdReMapperProcessor;

    // Nested LogPipeline
    private LogPipeline nestedPipelineProcessor;



}
