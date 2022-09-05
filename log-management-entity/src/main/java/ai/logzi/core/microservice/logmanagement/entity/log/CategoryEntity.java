package ai.logzi.core.microservice.logmanagement.entity.log;

import ai.logzi.core.microservice.logmanagement.entity.share.FilterEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryEntity {
    private FilterEntity filter;
    private String name;
}
