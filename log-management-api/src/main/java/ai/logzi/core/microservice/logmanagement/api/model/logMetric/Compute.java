package ai.logzi.core.microservice.logmanagement.api.model.logMetric;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Compute {

    @NotBlank(message = "log.metric.aggregation_type.absent")
    @JsonProperty("aggregation_type")
    private String aggregation_type;

    private String path;
}
