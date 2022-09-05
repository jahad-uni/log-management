package ai.logzi.core.management.logmanagement.service.dto.log.processor;

import lombok.Data;

import java.util.List;

@Data
public class LookupProcessorDto {
    private String source;
    private String target;
    private List<String> lookup_table;
    private String default_lookup;
}
