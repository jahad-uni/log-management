package ai.logzi.core.management.logmanagement.service.mapper;

import ai.logzi.core.management.logmanagement.service.dto.log.LogPipelineDto;
import ai.logzi.core.microservice.logmanagement.entity.log.LogPipelineEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper
public interface LogPipelineDtoMapper {

    void toExistedLogPipelineEntity(LogPipelineDto logPipelineDto,
                                    @MappingTarget LogPipelineEntity existedLogPipelineEntity);

    LogPipelineEntity toLogPipelineEntity(String tenantId, LogPipelineDto logPipelineDto);

    LogPipelineDto fromLogPipelineEntity(LogPipelineEntity logPipelineEntity);

    List<LogPipelineDto> fromLogPipelineEntityList(List<LogPipelineEntity> logPipelineEntities);


}
