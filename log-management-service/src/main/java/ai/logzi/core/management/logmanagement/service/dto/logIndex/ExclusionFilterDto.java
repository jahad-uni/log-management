package ai.logzi.core.management.logmanagement.service.dto.logIndex;

import ai.logzi.core.management.logmanagement.service.validation.dto.FieldErrorDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExclusionFilterDto {

    private IndexFilterDto filter;
    private Boolean is_enabled;
    private String name;

    //other use
    private List<FieldErrorDto> fieldErrorDtoList;
    private String index;
}
