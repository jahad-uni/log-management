package ai.logzi.core.microserice.logmanagement.aspect.logging;

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
    public void beforeAdvice(JoinPoint joinPoint){

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

        //Get intercepted method details
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();

        //get parameter name
        System.out.println(Arrays.toString(methodSignature.getParameterNames()));

        // logging
        LOGGER.info("call " + methodName + " method in " + className);

        // get argument value
        Object[] objects = joinPoint.getArgs();
        System.out.println(Arrays.toString(objects));
        System.out.println(joinPoint.toString());

    }

    // The first way :
    @After(value = "execution(* ai.logzi.core.microservice.logmanagement.api.controller.LogPipelineController.*(..)))")
    public void afterAdvice(JoinPoint joinPoint){

        // logging
        LOGGER.info("finish method 1");

    }

    // The second way :
    @Pointcut("execution(* ai.logzi.core.microservice.logmanagement.api.*.LogPipelineController.*(..)))")
    public void employeeControllerLog2(){}

    @After("employeeControllerLog2()")
    public void afterAdvice2(JoinPoint joinPoint){

        // logging
        LOGGER.info("finish method 2");

    }

    //the third way :
//    @After("ai.logzi.core.microserice.logmanagement.api.logging.logPipelineControllerLog()")
//    public void afterAdvice3(JoinPoint joinPoint){
//
//        // logging
//        LOGGER.info("finish method 3");
//
//    }

    //**********************************************************************************

    //    @Around : Advice that surrounds a join point such as a method invocation.
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
//        return ResponseEntity.ok("result");

    }
}
