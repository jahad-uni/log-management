package ai.logzi.core.management.logmanagement.service.exception;

import ai.logzi.core.microservice.logmanagement.common.constant.I18Constant;
import lombok.Getter;

@Getter
public class NestedLogPipelineProcessorException extends RuntimeException{

    private final String code = I18Constant.LOG_PROCESSOR_PIPELINE_NESTED_PIPELINE_NOT_VALID.getCode();

    public NestedLogPipelineProcessorException(){
        super("Nested LogPipeline Processor in another Nested LogPipeline Processor not valid");
    }
}
