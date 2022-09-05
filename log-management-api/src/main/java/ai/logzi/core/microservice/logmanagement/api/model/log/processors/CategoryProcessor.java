package ai.logzi.core.microservice.logmanagement.api.model.log.processors;

import ai.logzi.core.microservice.logmanagement.api.model.log.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryProcessor {
    private List<Category> categories;
    private String target;
}
