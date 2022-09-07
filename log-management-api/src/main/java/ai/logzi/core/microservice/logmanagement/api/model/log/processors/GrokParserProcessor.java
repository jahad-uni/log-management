package ai.logzi.core.microservice.logmanagement.api.model.log.processors;

import ai.logzi.core.microservice.logmanagement.api.model.log.processors.model.Grok;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GrokParserProcessor {

    @NotNull(message = "source is empty dudeeeee")
    private String source;
    private Grok grok;
    private List<String> samples;
    @JsonProperty("helper_rules")
    private List<String> helperRules;
}

