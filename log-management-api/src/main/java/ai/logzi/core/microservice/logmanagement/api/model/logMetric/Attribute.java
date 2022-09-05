package ai.logzi.core.microservice.logmanagement.api.model.logMetric;

import ai.logzi.core.microservice.logmanagement.api.model.Filter;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Attribute {
    @Valid
    @NotNull(message = "{log.metric.compute.absent}")
    private Compute compute;

    @Valid
    @JsonProperty("group_by")
    private List<GroupBy> group_by;

    private Filter filter;
}
