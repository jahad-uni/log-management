package ai.logzi.core.microservice.logmanagement.api.model.logIndex;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;


@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExclusionFilter {

    @Valid
    private IndexFilter filter;

    @JsonProperty("is_enabled")
    private Boolean is_enabled;

    @NotBlank(message = "{log.index.exclusion.filter.name.absent}")
    private String name;
}
