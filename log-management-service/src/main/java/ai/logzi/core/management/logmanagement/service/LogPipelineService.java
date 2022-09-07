package ai.logzi.core.management.logmanagement.service;

import ai.logzi.core.management.logmanagement.service.dto.log.LogPipelineDto;
import ai.logzi.core.management.logmanagement.service.dto.log.processor.model.LogPipelineFilterDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LogPipelineService {

    List<LogPipelineDto> getAllLogPipelinesOrderByOrder(final String tenantId);

    // Move to mapper
    LogPipelineFilterDto generateLogPipelineFilterDto(final String search);

    List<LogPipelineDto> getAllLogPipelinesOrderByOrder(final String tenantId,
                                                        final LogPipelineFilterDto logPipelineFilterDto);

    LogPipelineDto getLogPipeline(final String tenantId,
                                  final String id);

    LogPipelineDto createLogPipeline(final String tenantId,
                                     final LogPipelineDto logPipelineDto) throws Exception;


    LogPipelineDto updateLogPipeline(final String tenantId,
                                     final LogPipelineDto logPipelineDto) throws Exception;


    void deleteLogPipeline(final String tenantId,
                           final String userId,
                           final String id);

    void deleteAllLogPipelines(final String tenantId);

    List<String> getUpdatedByIdsDistinct(final String tenantId);
}
