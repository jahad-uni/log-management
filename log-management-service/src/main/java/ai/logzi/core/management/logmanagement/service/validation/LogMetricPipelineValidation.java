package ai.logzi.core.management.logmanagement.service.validation;

import ai.logzi.core.management.logmanagement.service.dto.logMetric.LogMetricPipelineDto;

public interface LogMetricPipelineValidation {
    LogMetricPipelineDto validateLogMetric(final LogMetricPipelineDto logMetricPipelineDto) throws Exception;
}
