package ai.logzi.core.microservice.logmanagement.api.model.log.processors.spec;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    private Filter filter;
    private String name;
}
