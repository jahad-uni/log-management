package ai.logzi.core.microservice.logmanagement.aop.security;

import ai.logzi.core.microservice.logmanagement.aop.Exception.UserNotAuthorizedException;
import ai.logzi.core.microservice.logmanagement.api.annotation.CheckRole;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Aspect
@Component
public class CheckRoleAspect {

    private static final Logger LOGGER = LogManager.getLogger(CheckRoleAspect.class);

    @Before("@annotation(ai.logzi.core.microservice.logmanagement.api.annotation.CheckRole)")
    public void checkRoleBefore(JoinPoint joinPoint) throws Exception {

        String[] roles = getRoles(joinPoint);
        List<String> userRoles = new ArrayList<>();
        boolean isAuth = false;
        LOGGER.info("Before execution - Roles: " + Arrays.toString(roles));
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .currentRequestAttributes())
                .getRequest();
        String authorization = request.getHeader("Authorization");
        if (authorization != null) {
            String authCode = authorization.replace("Bearer ", "");
            if (authCode.equals("111111"))
                userRoles.add("admin");
            if (authCode.equals("222222"))
                userRoles.add("editor");
            if (authCode.equals("333333"))
                userRoles.add("user");
        }
        if(roles != null && roles.length > 0) {
            for (String role : roles) {
                if (userRoles.contains(role))
                    isAuth = true;
            }
        }
        if(!isAuth)
            throw new UserNotAuthorizedException("user.not.authorized");
    }

    @AfterReturning(pointcut = "@annotation(ai.logzi.core.microservice.logmanagement.api.annotation.CheckRole)")
    public void checkRoleAfter(JoinPoint joinPoint) {
        LOGGER.info("After execution - Roles: " + Arrays.toString(getRoles(joinPoint)));

    }

    @AfterThrowing(pointcut = "@annotation(ai.logzi.core.microservice.logmanagement.api.annotation.CheckRole)", throwing = "ex")
    public void checkRoleAfterThrowingAnException(JoinPoint joinPoint, Exception ex) {
        LOGGER.info("After throwing an exception - Roles:" + Arrays.toString(getRoles(joinPoint)) + ex);
    }

    @Around("@annotation(ai.logzi.core.microservice.logmanagement.api.annotation.CheckRole)")
    public Object checkRoleAround(ProceedingJoinPoint joinPoint) throws Throwable {
        LOGGER.info("Before in Around execution. - Roles: " + Arrays.toString(getRoles(joinPoint)));
        Object result = joinPoint.proceed();
        System.out.println("After in arround execution");
        return result;
    }


    private String[] getRoles(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        CheckRole checkRole = method.getAnnotation(CheckRole.class);
        return checkRole.roles();
    }

}
