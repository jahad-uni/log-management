package ai.logzi.core.microservice.logmanagement.entity.log.processor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArithmeticProcessorEntity {
    private String expression;
    private Boolean is_replace_missing;
    private String target;
}
