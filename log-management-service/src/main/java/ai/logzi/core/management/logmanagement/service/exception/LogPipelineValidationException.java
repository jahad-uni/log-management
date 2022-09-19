package ai.logzi.core.management.logmanagement.service.exception;

import ai.logzi.core.management.logmanagement.service.validation.dto.FieldErrorDto;
import ai.logzi.core.microservice.logmanagement.common.constant.I18Constant;
import lombok.Getter;
import org.springframework.validation.FieldError;

import java.util.Arrays;
import java.util.List;

@Getter
public class LogPipelineValidationException extends RuntimeException {

    private final List<FieldErrorDto> fieldErrors ;

    private final String code = I18Constant.LOG_PIPELINE_VALIDATION_ERROR.getCode();

    public LogPipelineValidationException(List<FieldErrorDto> fieldErrors) {
        super("LogPipeline Validation Exception: " + Arrays.toString(fieldErrors.toArray()));
        this.fieldErrors = fieldErrors;
    }
}
