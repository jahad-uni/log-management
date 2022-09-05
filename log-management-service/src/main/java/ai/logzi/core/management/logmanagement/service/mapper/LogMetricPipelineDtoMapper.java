package ai.logzi.core.management.logmanagement.service.mapper;

import ai.logzi.core.management.logmanagement.service.dto.logMetric.LogMetricPipelineDto;
import ai.logzi.core.microservice.logmanagement.entity.logMetric.LogMetricPipelineEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper
public interface LogMetricPipelineDtoMapper {

    void toExistedLogMetricEntity(LogMetricPipelineDto logMetricPipelineDto,
                                  @MappingTarget LogMetricPipelineEntity existedLogMetricEntity);

    LogMetricPipelineEntity toLogMetricPipelineEntity(String tenantId, LogMetricPipelineDto logMetricPipelineDto);

    LogMetricPipelineDto fromLogMetricPipelineEntity(LogMetricPipelineEntity logMetricPipelineEntity);

    List<LogMetricPipelineDto> fromLogMetricPipelineEntityList(List<LogMetricPipelineEntity> logMetricPipelineEntities);


}
