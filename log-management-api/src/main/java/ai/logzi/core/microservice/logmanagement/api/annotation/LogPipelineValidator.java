package ai.logzi.core.microservice.logmanagement.api.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

import ai.logzi.core.microservice.logmanagement.api.annotation.impl.LogPipelineValidatorImpl;

@Constraint(validatedBy = LogPipelineValidatorImpl.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface LogPipelineValidator {
    String message() default "{error.address}";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
