package ai.logzi.core.microservice.logmanagement.aop.Exception;

import ai.logzi.core.microservice.logmanagement.common.constant.I18Constant;
import lombok.Getter;

@Getter
public class UserNotAuthorizedException extends RuntimeException {

    private final String code = I18Constant.USER_NOT_AUTHORIZED.getCode();

    public UserNotAuthorizedException(){
        super("User not authorized");
    }
}
