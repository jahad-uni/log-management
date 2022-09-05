package ai.logzi.core.microservice.logmanagement.api.model.log;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogPipelineInput {

    //    @NotBlank(message = "{name.notempty}")
    private String name;
    private List<String> tags;
    private String description;
    private List<String> filters;
    private String type;

    @JsonProperty("is_enabled")
    private boolean is_enabled;

    private List<LogPipelineProcessor> processors = new ArrayList<>();
}
