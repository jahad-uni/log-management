package ai.logzi.core.microservice.logmanagement.aop.Exception;

public class UserNotAuthorizedException extends RuntimeException {

    public UserNotAuthorizedException(String message){
        super(message);
    }
}
