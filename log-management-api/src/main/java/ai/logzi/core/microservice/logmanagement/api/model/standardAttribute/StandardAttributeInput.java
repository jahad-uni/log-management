package ai.logzi.core.microservice.logmanagement.api.model.standardAttribute;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StandardAttributeInput {

    @NotBlank(message = "standard.attribute.attribute.absent")
    private String attribute;

    @NotBlank(message = "standard.attribute.attribute.type.absent")
    private String type;

    private String description;
    private String remapping;
}
