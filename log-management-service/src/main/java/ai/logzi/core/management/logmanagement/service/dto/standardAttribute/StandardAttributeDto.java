package ai.logzi.core.management.logmanagement.service.dto.standardAttribute;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StandardAttributeDto {

    private String id;
    private String attribute;
    private String type;
    private String description;
    private String remapping;


    private String userId;

    private String updatedBy;
    private LocalDateTime updatedAt;

}
