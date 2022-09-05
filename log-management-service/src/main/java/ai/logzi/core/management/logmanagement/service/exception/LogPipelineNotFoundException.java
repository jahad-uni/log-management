package ai.logzi.core.management.logmanagement.service.exception;

import lombok.Getter;

@Getter
public class LogPipelineNotFoundException extends RuntimeException {

    private final String id;

    public LogPipelineNotFoundException(String message,String id){
        super(message);
        this.id = id;

    }

}
