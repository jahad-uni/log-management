package ai.logzi.core.microservice.logmanagement.api.model.log.processors;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UrlParserProcessor {
    private List<String> sources;

    @JsonProperty("normalize_ending_slashes")
    private Boolean normalize_ending_slashes;

    private String target;
}
