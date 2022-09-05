package ai.logzi.core.microservice.logmanagement.api.model.logIndex;


import ai.logzi.core.microservice.logmanagement.api.model.Filter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;


@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IndexFilter extends Filter {

    @JsonProperty("sample_rate")
    @Max(value =100, message="log.index.exclusion.filter.filter.sample.rate.range")
    @Min(value =0, message="log.index.exclusion.filter.filter.sample.rate.range")
    private int sample_rate;

    @NotBlank(message = "log.index.sample.rate.type.absent")
    @JsonProperty("sample_rate_type")
    private String sample_rate_type;

}
