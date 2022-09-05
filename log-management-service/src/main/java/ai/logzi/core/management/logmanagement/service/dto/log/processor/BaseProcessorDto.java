package ai.logzi.core.management.logmanagement.service.dto.log.processor;

import lombok.Data;

@Data
public class BaseProcessorDto {

    private String id;
    private String name;
    private boolean is_enabled;
    private String type;
}
