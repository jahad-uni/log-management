package ai.logzi.core.microservice.logmanagement.entity.log.processor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAgentParserProcessorEntity {
    private List<String> sources;
    private Boolean is_encoded;
    private String target;
}
