package ai.logzi.core.microservice.logmanagement.api.mapper;

import ai.logzi.core.management.logmanagement.service.LogPipelineService;
import ai.logzi.core.management.logmanagement.service.dto.log.LogPipelineDto;
import ai.logzi.core.microservice.logmanagement.api.model.log.LogPipeline;
import lombok.AllArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class LogPipelineCustomMapper {

    @Autowired
    protected LogPipelineService logPipelineService;

    @Mapping(target = "emailBox", expression = "java(logPipelineService.getEmailAddress(logPipeline.getEmail()))")
    public abstract LogPipelineDto toLogPipeLineDto(String tenantId,
                                    String userId,
                                    LogPipeline logPipeline);

}
