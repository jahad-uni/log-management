package ai.logzi.core.microservice.logmanagement.api.mapper;


import ai.logzi.core.management.logmanagement.service.dto.log.LogPipelineDto;
import ai.logzi.core.management.logmanagement.service.dto.log.LogPipelineOrderDto;
import ai.logzi.core.microservice.logmanagement.api.model.log.LogPipeline;
import ai.logzi.core.microservice.logmanagement.api.model.log.LogPipelineInput;
import ai.logzi.core.microservice.logmanagement.api.model.log.LogPipelineOrder;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface LogPipelineMapper {

    LogPipelineDto toLogPipeLineDto   (String tenantId,
                                       String userId,
                                       LogPipelineInput logPipelineInput);

    LogPipelineDto toLogPipeLineDto(String tenantId,
                                    String userId,
                                    String id,
                                    LogPipelineInput logPipelineInput);

    List<LogPipelineDto> toLogPipeLineDtoList(List<LogPipeline> logPipelines);

    LogPipeline fromLogPipelineDto(LogPipelineDto logPipelineDto);

    List<LogPipeline> fromLogPipelineDtoList(List<LogPipelineDto> logPipelineDtoList);

    LogPipelineOrderDto toLogPipelineOrderDto(String tenantId,
                                              String userId,
                                              LogPipelineOrder logPipelineOrder);

    LogPipelineOrder fromLogPipelineOrderDto(LogPipelineOrderDto logPipelineOrderDto);

}
