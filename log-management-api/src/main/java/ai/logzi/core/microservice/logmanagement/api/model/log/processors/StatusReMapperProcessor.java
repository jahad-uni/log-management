package ai.logzi.core.microservice.logmanagement.api.model.log.processors;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatusReMapperProcessor {
    private List<String> sources;
}
