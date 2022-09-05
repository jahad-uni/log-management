package ai.logzi.core.management.logmanagement.service.dto.log.processor;

import lombok.Data;

import java.util.List;

@Data
public class AttributeReMapperProcessorDto  {
    private Boolean override_on_conflict;
    private Boolean preserve_source;
    private List<String> sources;
    private String sourceType;
    private String target;
    private String targetType;
    private String targetFormat;
}
