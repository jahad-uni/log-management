package ai.logzi.core.management.logmanagement.service.dto.log.processor;

import ai.logzi.core.management.logmanagement.service.dto.log.GrokDto;
import lombok.Data;

import java.util.List;

@Data
public class GrokParserProcessorDto {

    private GrokDto grok;
    private List<String> samples;
    private List<String> helperRules;
    private String source;

}
