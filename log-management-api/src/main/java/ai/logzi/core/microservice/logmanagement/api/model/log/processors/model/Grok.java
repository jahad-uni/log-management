package ai.logzi.core.microservice.logmanagement.api.model.log.processors.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(name="Grok")
public class Grok {

    private String match_rules;
    private String support_rules;
}
