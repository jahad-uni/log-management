package ai.logzi.core.microservice.logmanagement.aop.Exception;

import ai.logzi.core.management.logmanagement.service.exception.NestedLogPipelineProcessorException;
import ai.logzi.core.management.logmanagement.service.exception.LogPipelineNotFoundException;
import ai.logzi.core.management.logmanagement.service.exception.LogPipelineValidationException;
import ai.logzi.core.microservice.logmanagement.common.helper.LocaleHelper;
import ai.logzi.core.microservice.logmanagement.aop.Exception.dto.ErrorResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@Slf4j()
@ControllerAdvice
@AllArgsConstructor
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    public static final String TRACE = "trace";
    private LocaleHelper localeHelper;

    @ExceptionHandler(UserNotAuthorizedException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> handleUserNotAuthorizedException(UserNotAuthorizedException exception, WebRequest request) {
        log.error(exception.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now().toString(),
                HttpStatus.BAD_REQUEST.value(),
                localeHelper.getLocalMessage(exception.getCode()));
        return ResponseEntity.unprocessableEntity().body(errorResponse);
    }

    @ExceptionHandler(NestedLogPipelineProcessorException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> handleLogPipelineException(NestedLogPipelineProcessorException exception, WebRequest request) {
        log.error(exception.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now().toString(),
                HttpStatus.BAD_REQUEST.value(),
                localeHelper.getLocalMessage(exception.getCode()));
        return ResponseEntity.unprocessableEntity().body(errorResponse);

    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(LogPipelineValidationException.class)
    protected ResponseEntity<Object> LogPipelineValidationException(LogPipelineValidationException exception) {
        log.error(exception.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now().toString(),
                HttpStatus.BAD_REQUEST.value(),
                localeHelper.getLocalMessage(exception.getCode()));
        for (var fieldError : exception.getFieldErrors()) {
            errorResponse.addValidationError(fieldError.getField(),
                    localeHelper.getLocalMessage(fieldError.getError()));
        }
        return ResponseEntity.unprocessableEntity().body(errorResponse);
    }

    @ExceptionHandler(LogPipelineNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleLogPipelineNotFoundException(LogPipelineNotFoundException exception
            , WebRequest request) {
        log.error(exception.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now().toString(),
                HttpStatus.BAD_REQUEST.value(),
                localeHelper.getLocalMessage(exception.getCode(), exception.getId()));
        return ResponseEntity.unprocessableEntity().body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> handleAllUncaughtException(Exception exception, WebRequest request) {
        log.error("Unknown error occurred", exception);
        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now().toString(),
                400,
                localeHelper.getLocalMessage(exception.getMessage()));
        return ResponseEntity.unprocessableEntity().body(errorResponse);
    }


    @Override
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        log.error(exception.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now().toString(),
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                "Validation error. Check 'errors' field for details.");
        for (FieldError fieldError : exception.getBindingResult().getFieldErrors()) {
            errorResponse.addValidationError(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return ResponseEntity.unprocessableEntity().body(errorResponse);
    }

    @Override
    public ResponseEntity<Object> handleExceptionInternal(Exception exception,
                                                          Object body,
                                                          HttpHeaders headers,
                                                          HttpStatus status,
                                                          WebRequest request) {
        log.error(exception.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now().toString(),
                400,
                localeHelper.getLocalMessage(exception.getMessage()));
        return ResponseEntity.unprocessableEntity().body(errorResponse);
    }
}
