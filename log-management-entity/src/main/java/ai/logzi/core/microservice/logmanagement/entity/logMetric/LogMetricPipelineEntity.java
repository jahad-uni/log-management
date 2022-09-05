package ai.logzi.core.microservice.logmanagement.entity.logMetric;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@Document(collection = "logMetrics")
@CompoundIndexes({
        @CompoundIndex(def = "{'tenantId' : 1, 'name' : 1}", unique = true)
})
public class LogMetricPipelineEntity {

    @Id
    private String id ;
    private String name;
    private String type;
    private AttributeEntity attributes;

    private String tenantId;
    private boolean isPublished;

    private String createdBy;
    private String updatedBy;
    private String publishedBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt ;
    private LocalDateTime publishedAt;
}
