package ai.logzi.core.management.logmanagement.service.dto.standardAttribute;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StandardAttributeOrderDto {

    private String userId;
    private List<String> ids;
}
