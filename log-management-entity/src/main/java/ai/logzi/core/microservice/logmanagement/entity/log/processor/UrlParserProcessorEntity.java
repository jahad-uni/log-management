package ai.logzi.core.microservice.logmanagement.entity.log.processor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UrlParserProcessorEntity {
    private List<String> sources;
    private Boolean normalize_ending_slashes;
    private String target;
}
