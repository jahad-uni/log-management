package ai.logzi.core.microservice.logmanagement.entity.logIndex;

import lombok.Data;

@Data
public class ExclusionFilterEntity {

    private IndexFilterEntity filter;
    private Boolean is_enabled;
    private String name;
}
