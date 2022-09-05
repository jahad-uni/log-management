package ai.logzi.core.microservice.logmanagement.api.model.logIndex;

import ai.logzi.core.microservice.logmanagement.api.model.Filter;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogIndexInput {

    @JsonProperty("daily_limit")
    private long daily_limit;

    @Valid
    private Filter filter;

    @NotBlank(message = "{log.index.name.absent}")
    @Pattern(regexp = "^[a-z][a-z0-9-]*(?:_[a-z0-9-]+)*$" , message = "{log.index.name.pattern.invalid}")
    private String name;

    @JsonProperty("num_retention_days")
    private long num_retention_days;

    @JsonProperty("is_rate_limited")
    private boolean is_rate_limited;

    @Valid
    @JsonProperty("exclusion_filters")
    private List<ExclusionFilter> exclusion_filters;

}
