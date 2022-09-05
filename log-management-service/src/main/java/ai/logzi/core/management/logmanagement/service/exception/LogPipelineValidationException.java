package ai.logzi.core.management.logmanagement.service.exception;

import ai.logzi.core.management.logmanagement.service.validation.dto.FieldErrorDto;
import lombok.Getter;

import java.util.List;

@Getter
public class LogPipelineValidationException extends RuntimeException {

    private final List<FieldErrorDto> fieldErrors ;

    public LogPipelineValidationException(String message, List<FieldErrorDto> fieldErrors) {
        super(message);
        this.fieldErrors = fieldErrors;
    }
}
