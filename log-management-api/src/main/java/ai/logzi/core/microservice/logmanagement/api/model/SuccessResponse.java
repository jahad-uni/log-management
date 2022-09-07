package ai.logzi.core.microservice.logmanagement.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class SuccessResponse<T> {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final T data;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private final int status;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final String message;
}
