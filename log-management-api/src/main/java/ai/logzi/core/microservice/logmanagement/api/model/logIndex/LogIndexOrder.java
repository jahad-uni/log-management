package ai.logzi.core.microservice.logmanagement.api.model.logIndex;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogIndexOrder {

    @NotEmpty
    private List<String> index_names;

}
