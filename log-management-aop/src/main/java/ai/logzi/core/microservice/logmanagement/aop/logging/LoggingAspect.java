package ai.logzi.core.microservice.logmanagement.aop.logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger LOGGER = LogManager.getLogger(LoggingAspect.class);

    @Before(value = "execution(* ai.logzi.core.microservice.logmanagement.api.controller.LogPipelineController.*(..)))")
    public void beforeLogging(JoinPoint joinPoint){

        LOGGER.info("Logging...Before LogPipelineController Rest call");
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

        //Get intercepted method details
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();
        String[] methodParameters = methodSignature.getParameterNames();

        //logging class and method name
        LOGGER.info("call " + methodName + " method in " + className);

        //logging method parameters
        LOGGER.info("Method Parameters: " + Arrays.toString(methodParameters));

        // get argument value
        Object[] objects = joinPoint.getArgs();
        LOGGER.info(Arrays.toString(objects));
        LOGGER.info(joinPoint.toString());

    }

    // The first way :
    @After(value = "execution(* ai.logzi.core.microservice.logmanagement.api.controller.LogPipelineController.*(..)))")
    public void afterLogging(JoinPoint joinPoint){

        // logging
        LOGGER.info("Logging...After LogPipelineController Rest call");

    }

    // The second way :
    @Pointcut("execution(* ai.logzi.core.microservice.logmanagement.api.*.LogPipelineController.*(..)))")
    public void logPipelineControllerLog(){}

    @Before("logPipelineControllerLog()")
    public void beforAdvice2(JoinPoint joinPoint){

        // logging
        LOGGER.info("Logging...Before Pointcut");

    }

    @After("logPipelineControllerLog()")
    public void afterAdvice2(JoinPoint joinPoint){

        // logging
        LOGGER.info("Logging...After Pointcut");

    }

    @Around(value = "execution(* ai.logzi.core.microservice.logmanagement.api.controller.LogPipelineController.*(..)))")
    public Object AroundAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object result = proceedingJoinPoint.proceed();
        stopWatch.stop();

        //Log method execution time
        LOGGER.info("Execution time of "
                + ":: " + stopWatch.getTotalTimeMillis() + " ms");

        return result;
    }
}
