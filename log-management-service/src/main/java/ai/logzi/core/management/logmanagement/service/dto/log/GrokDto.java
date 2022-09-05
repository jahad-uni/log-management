package ai.logzi.core.management.logmanagement.service.dto.log;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GrokDto {

    private String match_rules;
    private String support_rules;
}
