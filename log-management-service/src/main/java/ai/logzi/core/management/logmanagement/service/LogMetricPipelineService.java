package ai.logzi.core.management.logmanagement.service;

import ai.logzi.core.management.logmanagement.service.dto.logMetric.LogMetricPipelineDto;

import java.util.List;

public interface LogMetricPipelineService {
    List<LogMetricPipelineDto> getAllLogMetrics(final String tenantId);

    LogMetricPipelineDto getLogMetric(final String tenantId,
                                      final String id);

    LogMetricPipelineDto createLogMetric(final String tenantId,
                                         final LogMetricPipelineDto logMetricPipelineDto) throws Exception;

    LogMetricPipelineDto updateLogMetric(final String tenantId,
                                         final String id,
                                         final LogMetricPipelineDto logMetricPipelineDto) throws Exception;

    void deleteLogMetric(final String tenantId,
                         final String userId,
                         final String id);

}
