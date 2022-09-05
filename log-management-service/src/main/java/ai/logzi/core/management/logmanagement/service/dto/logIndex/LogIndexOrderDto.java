package ai.logzi.core.management.logmanagement.service.dto.logIndex;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogIndexOrderDto {

    private String userId;
    private List<String> index_names;
}
