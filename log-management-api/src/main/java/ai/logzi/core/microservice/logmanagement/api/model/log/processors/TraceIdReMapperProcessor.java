package ai.logzi.core.microservice.logmanagement.api.model.log.processors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TraceIdReMapperProcessor {
    private List<String> sources;
}
