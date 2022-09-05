package ai.logzi.core.management.logmanagement.service.dto.log.processor;

import lombok.Data;

@Data
public class StringBuilderProcessorDto  {
    private Boolean is_replace_missing;
    private String target;
    private String template;
}
