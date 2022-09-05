package ai.logzi.core.microservice.logmanagement.api.model.log.processors;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttributeReMapperProcessor {

    @JsonProperty("override_on_conflict")
    private Boolean override_on_conflict;

    @JsonProperty("preserve_source")
    private Boolean preserve_source;

    private List<String> sources;

    @JsonProperty("source_type")
    private String sourceType;

    private String target;

    @JsonProperty("target_type")
    private String targetType;

    @JsonProperty("target_format")
    private String targetFormat;
}
