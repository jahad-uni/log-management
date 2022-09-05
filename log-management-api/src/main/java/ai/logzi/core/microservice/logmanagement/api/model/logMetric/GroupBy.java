package ai.logzi.core.microservice.logmanagement.api.model.logMetric;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupBy {

    @NotBlank(message = "{log.metric.group.by.path.absent}")
    private String path;

    @JsonProperty("tag_name")
    private String tag_name;
}
