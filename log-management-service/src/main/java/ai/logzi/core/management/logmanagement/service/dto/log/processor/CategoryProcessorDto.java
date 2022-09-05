package ai.logzi.core.management.logmanagement.service.dto.log.processor;

import ai.logzi.core.management.logmanagement.service.dto.log.CategoryDto;
import lombok.Data;

import java.util.List;

@Data
public class CategoryProcessorDto{
    private List<CategoryDto> categories;
    private String target;
}
