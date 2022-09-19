package ai.logzi.core.management.logmanagement.service.exception;

import ai.logzi.core.microservice.logmanagement.common.constant.I18Constant;
import lombok.Getter;

@Getter
public class LogPipelineNotFoundException extends RuntimeException {

    private final String id;
    private final String code = I18Constant.LOG_PIPELINE_NOT_FOUND.getCode();

    public LogPipelineNotFoundException(String id){
        super("LogPipeline with id = "+id+" not found");
        this.id = id;

    }

}
