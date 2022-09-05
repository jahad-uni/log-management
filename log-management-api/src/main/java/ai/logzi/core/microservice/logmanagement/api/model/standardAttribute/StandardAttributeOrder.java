package ai.logzi.core.microservice.logmanagement.api.model.standardAttribute;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(name="StandardAttributeOrder")
public class StandardAttributeOrder {

    @NotEmpty
    private List<String> attributes;

}
