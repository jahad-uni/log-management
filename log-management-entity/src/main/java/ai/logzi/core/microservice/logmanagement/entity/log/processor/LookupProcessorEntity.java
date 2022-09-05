package ai.logzi.core.microservice.logmanagement.entity.log.processor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LookupProcessorEntity {
    private String source;
    private String target;
    private List<String> lookup_table;
    private String default_lookup;
}
