package ai.logzi.core.microservice.logmanagement.entity.log.processor;

import ai.logzi.core.microservice.logmanagement.entity.log.GrokEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GrokParserProcessorEntity {
    private String source;
    private GrokEntity grok;
    private List<String> samples;
    private List<String> helperRules;
}
