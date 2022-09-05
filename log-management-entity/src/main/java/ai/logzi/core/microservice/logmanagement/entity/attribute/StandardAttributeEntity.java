package ai.logzi.core.microservice.logmanagement.entity.attribute;

import ai.logzi.core.microservice.logmanagement.entity.share.BaseEntity;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "standardAttributes")
@CompoundIndexes({
        @CompoundIndex(def = "{'tenantId' : 1, 'attribute' : 1}", unique = true)
})
public class StandardAttributeEntity extends BaseEntity {

    private String tenantId;
    private String attribute;
    private String type;
    private String description;
    private String remapping;

    private long order;
}
