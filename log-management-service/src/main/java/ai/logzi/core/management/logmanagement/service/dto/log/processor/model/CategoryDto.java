package ai.logzi.core.management.logmanagement.service.dto.log.processor.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {
    private FilterDto filter;
    private String name;
}
