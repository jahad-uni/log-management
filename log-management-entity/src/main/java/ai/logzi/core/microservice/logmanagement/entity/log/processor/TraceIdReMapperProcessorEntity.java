package ai.logzi.core.microservice.logmanagement.entity.log.processor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TraceIdReMapperProcessorEntity {
    private List<String> sources;
}
