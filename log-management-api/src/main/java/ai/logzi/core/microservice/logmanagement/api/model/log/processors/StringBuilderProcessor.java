package ai.logzi.core.microservice.logmanagement.api.model.log.processors;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StringBuilderProcessor {
    @JsonProperty("is_replace_missing")
    private Boolean is_replace_missing;
    private String target;
    private String template;
}
