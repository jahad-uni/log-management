package ai.logzi.core.microservice.logmanagement.api.model.standardAttribute;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StandardAttribute {

    private String id;
    private String attribute;
    private String type;
    private String description;
    private String remapping;

    private String updatedBy;
    private String updatedAt;
}
