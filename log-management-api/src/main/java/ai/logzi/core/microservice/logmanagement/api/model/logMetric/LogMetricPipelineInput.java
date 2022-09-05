package ai.logzi.core.microservice.logmanagement.api.model.logMetric;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogMetricPipelineInput {

    @NotBlank(message = "{log.metric.name.absent}")
    private String name;

    @NotBlank(message = "{log.metric.type.absent}")
    private String type;

    @Valid
    @NotNull(message = "{log.metric.attribute.absent}")
    private Attribute attributes;
}
