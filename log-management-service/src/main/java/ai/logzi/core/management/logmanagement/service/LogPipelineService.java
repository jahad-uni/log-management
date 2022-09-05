package ai.logzi.core.management.logmanagement.service;

import ai.logzi.core.management.logmanagement.service.dto.log.LogPipelineDto;
import ai.logzi.core.management.logmanagement.service.dto.log.LogPipelineOrderDto;
import ai.logzi.core.management.logmanagement.service.dto.log.filter.LogPipelineFilterDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LogPipelineService {

    List<LogPipelineDto> getAllLogPipelinesOrderByOrder(final String tenantId);

    List<LogPipelineDto> getAllLogPipelinesOrderByName(final String tenantId,
                                                       final String type);

    // Move to mapper
    LogPipelineFilterDto generateLogPipelineFilterDto(final String search);

    List<LogPipelineDto> getAllLogPipelinesOrderByOrder(final String tenantId,
                                                        final LogPipelineFilterDto logPipelineFilterDto);

    LogPipelineDto getLogPipeline(final String tenantId,
                                  final String id);

    LogPipelineDto createLogPipeline(final String tenantId,
                                     final LogPipelineDto logPipelineDto) throws Exception;

    LogPipelineDto cloneLogPipeline(final String tenantId,
                                    final String userId,
                                    final String id) throws Exception;

    LogPipelineDto updateLogPipeline(final String tenantId,
                                     final LogPipelineDto logPipelineDto) throws Exception;

    List<LogPipelineDto> updateLogPipelines(final String tenantId,
                                            final List<LogPipelineDto> logPipelineDtoList,
                                            final String deletedId) throws Exception;

    LogPipelineOrderDto updateLogPipelinesOrder(final String tenantId,
                                                final LogPipelineOrderDto logPipelineOrderDto);

    List<String> getLogPipelineIdListOrderByOrder(final String tenantId);

    void deleteLogPipeline(final String tenantId,
                           final String userId,
                           final String id);

    void deleteAllLogPipelines(final String tenantId);

    List<String> getUpdatedByIdsDistinct(final String tenantId);
}
