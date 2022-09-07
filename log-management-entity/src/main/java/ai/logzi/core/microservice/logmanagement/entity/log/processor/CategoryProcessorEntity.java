package ai.logzi.core.microservice.logmanagement.entity.log.processor;


import ai.logzi.core.microservice.logmanagement.entity.log.processor.model.CategoryEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryProcessorEntity {
    private List<CategoryEntity> categories;
    private String target;
}
