package ai.logzi.core.microservice.logmanagement.entity.log;

import ai.logzi.core.microservice.logmanagement.entity.share.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "logPipelines")
public class LogPipelineEntity extends BaseEntity {

    private String tenantId;
    private String type;
    private String name;
    private List<String> tags;
    private String description;
    private List<String> filters;
    private boolean is_published;
    private boolean is_enabled;
    private boolean is_readonly;
    private int order;

    private List<LogPipelineProcessorEntity> processors;

}
