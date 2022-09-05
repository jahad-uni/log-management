package ai.logzi.core.microservice.logmanagement.api.model.log.processors;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAgentParserProcessor {
    private List<String> sources;
    @JsonProperty("is_encoded")
    private Boolean is_encoded;
    private String target;
}
