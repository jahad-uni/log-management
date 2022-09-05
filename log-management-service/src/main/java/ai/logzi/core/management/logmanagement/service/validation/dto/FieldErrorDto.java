package ai.logzi.core.management.logmanagement.service.validation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class FieldErrorDto {

    private String field;
    private String error;
}
