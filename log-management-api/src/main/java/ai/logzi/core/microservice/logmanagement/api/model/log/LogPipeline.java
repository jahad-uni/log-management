package ai.logzi.core.microservice.logmanagement.api.model.log;

import ai.logzi.core.microservice.logmanagement.common.validation.OnCreate;
import ai.logzi.core.microservice.logmanagement.common.validation.OnUpdate;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
//import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LogPipeline {

    @Null(groups = OnCreate.class)
    @Null(groups = OnUpdate.class)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private String id;

    @NotNull
    private String name;
    private List<String> tags;
    private String description;
    private List<String> filters;

    @NotNull
    private String type;

//    @Null(groups = OnCreate.class)
//    @Null(groups = OnUpdate.class)
    @Min(groups= OnCreate.class,value = 0)
    @Max(groups= OnCreate.class,value = 0)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private int order;

    @JsonProperty("is_enabled")
    private boolean is_enabled;

//    @Null(groups = OnCreate.class)
//    @Null(groups = OnUpdate.class)
//    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    @JsonProperty("is_readonly")
    private boolean is_readonly;

    @Null(groups = OnCreate.class)
    @Null(groups = OnUpdate.class)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    @JsonProperty("updated_by")
    private String updatedBy;

    @Null(groups = OnCreate.class)
    @Null(groups = OnUpdate.class)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    @JsonProperty("updated_at")
    private String updatedAt;

    private List<LogPipelineProcessor> processors = new ArrayList<>();
}
