package ai.logzi.core.microservice.logmanagement.api.model.logIndex;


import ai.logzi.core.microservice.logmanagement.api.model.Filter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LogIndex {

    private String id;
    @JsonProperty("daily_limit")
    private long daily_limit;

    private Filter filter;

    @NotBlank(message = "{log.index.name.absent}")
    private String name;

    @JsonProperty("num_retention_days")
    private long num_retention_days;

    @JsonProperty("is_rate_limited")
    private boolean is_rate_limited;
    
    @JsonProperty("exclusion_filters")
    private List<ExclusionFilter> exclusion_filters;

    private String updatedBy;
    private String updatedAt;
}
