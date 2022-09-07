package ai.logzi.core.microservice.logmanagement.entity.log.processor.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GrokEntity {

    private String match_rules;
    private String support_rules;
}
