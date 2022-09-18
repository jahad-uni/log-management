package ai.logzi.core.microservice.logmanagement.aop.Exception;

import ai.logzi.core.management.logmanagement.service.exception.LogPipelineException;
import ai.logzi.core.management.logmanagement.service.exception.LogPipelineIdListValidationException;
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
import java.util.Objects;

@Slf4j(topic = "GLOBAL_EXCEPTION_HANDLER")
@ControllerAdvice
@AllArgsConstructor
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    public static final String TRACE = "trace";
    private LocaleHelper localeHelper;

//    @Value("${reflectoring.trace:false}")
//    private boolean printStackTrace;

    @ExceptionHandler(UserNotAuthorizedException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> handleUserNotAuthorizedException(UserNotAuthorizedException exception, WebRequest request) {
        log.error("Authorization exception", exception);
        return buildErrorResponse(exception,
                localeHelper.getLocalMessage(exception.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR,
                request);
    }


    @ExceptionHandler(LogPipelineException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> handleLogPipelineException(LogPipelineException exception, WebRequest request) {
        log.error("Log pipeline exception", exception);
        return buildErrorResponse(exception,
                localeHelper.getLocalMessage(exception.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR,
                request);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(LogPipelineIdListValidationException.class)
    protected ResponseEntity<Object> handleLogPipelineIdListValidationException(LogPipelineIdListValidationException exception) {
        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now().toString(),
                HttpStatus.BAD_REQUEST.value(),
                localeHelper.getLocalMessage(exception.getMessage()));
        return ResponseEntity.unprocessableEntity().body(errorResponse);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(LogPipelineValidationException.class)
    protected ResponseEntity<Object> handleLogPipelineException(LogPipelineValidationException exception) {
        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now().toString(),
                HttpStatus.BAD_REQUEST.value(),
                localeHelper.getLocalMessage(exception.getMessage()));
        for (var fieldError : exception.getFieldErrors()) {
            errorResponse.addValidationError(fieldError.getField(),
                    localeHelper.getLocalMessage(fieldError.getError()));
        }
        return ResponseEntity.unprocessableEntity().body(errorResponse);
    }

    @Override
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now().toString(),
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                "Validation error. Check 'errors' field for details.");
        for (FieldError fieldError : exception.getBindingResult().getFieldErrors()) {
            errorResponse.addValidationError(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return ResponseEntity.unprocessableEntity().body(errorResponse);
    }

    @ExceptionHandler(LogPipelineNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleLogPipelineNotFoundException(LogPipelineNotFoundException exception
            , WebRequest request) {
        log.error("Failed to find the log pipeline with id " + exception.getId(), exception);
        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now().toString(),
                HttpStatus.BAD_REQUEST.value(),
                localeHelper.getLocalMessage(exception.getMessage(), exception.getId()));
        return ResponseEntity.unprocessableEntity().body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> handleAllUncaughtException(Exception exception, WebRequest request) {
        log.error("Unknown error occurred", exception);
        return buildErrorResponse(exception, "unknown error", HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    private ResponseEntity<Object> buildErrorResponse(Exception exception,
                                                      HttpStatus httpStatus,
                                                      WebRequest request) {
        return buildErrorResponse(exception, exception.getMessage(), httpStatus, request);
    }

    private ResponseEntity<Object> buildErrorResponse(Exception exception,
                                                      String message,
                                                      HttpStatus httpStatus,
                                                      WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now().toString(),
                httpStatus.value(),
                message);
//        if (printStackTrace && isTraceOn(request)) {
//            errorResponse.setStackTrace(ExceptionUtils.getStackTrace(exception));
//        }
        return ResponseEntity.status(httpStatus).body(errorResponse);
    }

    private boolean isTraceOn(WebRequest request) {
        String[] value = request.getParameterValues(TRACE);
        return Objects.nonNull(value)
                && value.length > 0
                && value[0].contentEquals("true");
    }

    @Override
    public ResponseEntity<Object> handleExceptionInternal(Exception ex,
                                                          Object body,
                                                          HttpHeaders headers,
                                                          HttpStatus status,
                                                          WebRequest request) {

        return buildErrorResponse(ex, status, request);
    }
}
