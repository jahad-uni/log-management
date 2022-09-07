package ai.logzi.core.microservice.logmanagement.api.model.log;

import ai.logzi.core.microservice.logmanagement.common.validation.OnCreate;
import ai.logzi.core.microservice.logmanagement.common.validation.OnUpdate;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LogPipeline {

    @Null(groups = OnCreate.class)
    @NotNull(groups = OnUpdate.class)
    @Schema(minLength = 3, accessMode = Schema.AccessMode.READ_ONLY)
    private String id;

    @NotNull(groups = {OnCreate.class, OnUpdate.class})
    @Size(groups = {OnCreate.class, OnUpdate.class}, min = 5, max = 15)
    private String name;

    @NotEmpty(groups = {OnCreate.class, OnUpdate.class})
    private List<String> tags;

    private String description;
    private List<String> filters;

    @NotNull(groups = {OnCreate.class, OnUpdate.class})
    private String type;

    @Min(groups = {OnCreate.class, OnUpdate.class}, value = 0)
    @Max(groups = {OnCreate.class, OnUpdate.class}, value = 0)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private int order;

    @JsonProperty("is_enabled")
    private boolean is_enabled;

    @JsonProperty("is_readonly")
    private boolean is_readonly;

    @Null(groups = {OnCreate.class, OnUpdate.class})
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    @JsonProperty("updated_by")
    private String updatedBy;

    @Null(groups = {OnCreate.class, OnUpdate.class})
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    @JsonProperty("updated_at")
    private String updatedAt;

    @Valid
    private List<LogPipelineProcessor> processors = new ArrayList<>();
}
