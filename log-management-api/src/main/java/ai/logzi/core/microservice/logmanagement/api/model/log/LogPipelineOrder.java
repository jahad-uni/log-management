package ai.logzi.core.microservice.logmanagement.api.model.log;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogPipelineOrder {

    @NotEmpty
    private List<String> pipeline_ids;

}
