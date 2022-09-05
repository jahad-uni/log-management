package ai.logzi.core.microservice.logmanagement.entity.logIndex;

import ai.logzi.core.microservice.logmanagement.entity.share.FilterEntity;
import lombok.Data;

@Data
public class IndexFilterEntity extends FilterEntity {

    private int sample_rate;
    private String sample_rate_type;
}
