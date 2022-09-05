package ai.logzi.core.microservice.logmanagement.entity.log.processor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttributeReMapperProcessorEntity {
    private Boolean override_on_conflict;
    private Boolean preserve_source;
    private List<String> sources;
    private String sourceType;
    private String target;
    private String targetType;
    private String targetFormat;
}
