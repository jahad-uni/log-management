package ai.logzi.core.microservice.logmanagement.api.model.log.processors;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LookupProcessor {

    private String source;
    private String target;

    @JsonProperty("lookup_table")
    private List<String> lookup_table;

    @JsonProperty("default_lookup")
    private String default_lookup;
}
