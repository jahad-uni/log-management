package ai.logzi.core.microservice.logmanagement.api.model.log;


import ai.logzi.core.microservice.logmanagement.api.model.Filter;
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
