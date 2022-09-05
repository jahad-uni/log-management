package ai.logzi.core.microservice.logmanagement.entity.logIndex;

import ai.logzi.core.microservice.logmanagement.entity.share.BaseEntity;
import ai.logzi.core.microservice.logmanagement.entity.share.FilterEntity;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder
@Document(collection = "logIndexes")
@CompoundIndexes({
        @CompoundIndex(def = "{'tenantId' : 1, 'name' : 1}", unique = true)
})
public class LogIndexEntity extends BaseEntity {

    private String name;
    private String tenantId;
    private long daily_limit;
    private FilterEntity filter;
    private long num_retention_days;
    private List<ExclusionFilterEntity> exclusion_filters;

    private boolean is_rate_limited;
    private long order;
}
