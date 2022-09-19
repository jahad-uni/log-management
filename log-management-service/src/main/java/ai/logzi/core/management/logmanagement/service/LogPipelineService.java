package ai.logzi.core.management.logmanagement.service;

import ai.logzi.core.management.logmanagement.service.dto.log.LogPipelineDto;
import ai.logzi.core.management.logmanagement.service.dto.log.processor.model.LogPipelineFilterDto;

import java.util.List;

public interface LogPipelineService {

    List<LogPipelineDto> getAllLogPipelines(final String tenantId);

    LogPipelineFilterDto generateLogPipelineFilterDto(final String search);

    List<LogPipelineDto> getAllLogPipelines(final String tenantId,
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


    String getEmailAddress(final String email);

}
