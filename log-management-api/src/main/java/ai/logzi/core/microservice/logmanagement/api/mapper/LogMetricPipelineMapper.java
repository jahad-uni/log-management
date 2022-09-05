package ai.logzi.core.microservice.logmanagement.api.mapper;

import ai.logzi.core.management.logmanagement.service.dto.logMetric.LogMetricPipelineDto;
import ai.logzi.core.microservice.logmanagement.api.model.logMetric.LogMetricPipeline;
import ai.logzi.core.microservice.logmanagement.api.model.logMetric.LogMetricPipelineInput;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface LogMetricPipelineMapper {

     LogMetricPipelineDto toLogMetricPipelineDto(String tenantId,
                                                       String userId,
                                                       LogMetricPipelineInput logMetricPipelineInput);

     LogMetricPipelineDto toLogMetricPipelineDto(String tenantId,
                                         String userId,
                                         String name,
                                         LogMetricPipelineInput logMetricPipelineInput);

     List<LogMetricPipelineDto> toLogMetricPipelineDtoList(List<LogMetricPipeline> logMetricPipelines);

    LogMetricPipeline fromLogMetricPipelineDto(LogMetricPipelineDto logMetricPipelineDto);

      List<LogMetricPipeline> fromLogMetricPipelineDtoList(List<LogMetricPipelineDto> logMetricPipelineDtoList);


}
