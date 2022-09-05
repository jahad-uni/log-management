package ai.logzi.core.microservice.logmanagement.entity.logArchive;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@Document(collection = "logArchivePipeline")
public class LogArchivePipelineEntity {

    @Id
    private String id ;
    private String tenantId;
    private String name;
    private boolean isPublished;
    private List<LogArchivePipelineProcessorEntity> logArchivePipelineProcessorEntities;

    private String createdBy;
    private String updatedBy;
    private String publishedBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt ;
    private LocalDateTime publishedAt;
}
