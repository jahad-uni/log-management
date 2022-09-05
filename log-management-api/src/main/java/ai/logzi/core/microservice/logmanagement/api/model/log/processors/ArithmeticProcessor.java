package ai.logzi.core.microservice.logmanagement.api.model.log.processors;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArithmeticProcessor {

    @JsonProperty("expression")
    private String expression;

    @JsonProperty("is_replace_missing")
    private Boolean is_replace_missing;

    private String target;
}
