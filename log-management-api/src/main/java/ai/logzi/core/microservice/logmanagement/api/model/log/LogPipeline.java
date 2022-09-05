package ai.logzi.core.microservice.logmanagement.api.model.log;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LogPipeline {

    private String id;
    private String name;
    private List<String> tags;
    private String description;
    private List<String> filters;
    private String type;
    private int order;
    @JsonProperty("is_enabled")
    private boolean is_enabled;
    @JsonProperty("is_readonly")
    private boolean is_readonly;
    @JsonProperty("updated_by")
    private String updatedBy;
    @JsonProperty("updated_at")
    private String updatedAt;
    private List<LogPipelineProcessor> processors = new ArrayList<>();
}
