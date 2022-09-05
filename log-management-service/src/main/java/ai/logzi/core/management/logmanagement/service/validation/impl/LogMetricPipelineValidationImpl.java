package ai.logzi.core.management.logmanagement.service.validation.impl;


import ai.logzi.core.management.logmanagement.service.constant.I18Constant;
import ai.logzi.core.management.logmanagement.service.dto.logMetric.LogMetricPipelineDto;
import ai.logzi.core.management.logmanagement.service.exception.LogPipelineValidationException;
import ai.logzi.core.management.logmanagement.service.validation.LogMetricPipelineValidation;
import ai.logzi.core.management.logmanagement.service.validation.util.LogMetricPipeLineValidationUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Getter
public class LogMetricPipelineValidationImpl implements LogMetricPipelineValidation {
    private final LogMetricPipeLineValidationUtil logMetricPipeLineValidationUtil;

    @Override
    public LogMetricPipelineDto validateLogMetric(LogMetricPipelineDto logMetricPipelineDto) throws Exception {
        var allErrors = this.logMetricPipeLineValidationUtil
                .validateLogMetric(logMetricPipelineDto);

        if (!allErrors.isEmpty())
            throw new LogPipelineValidationException(I18Constant.LOG_METRIC_VALIDATION_ERROR.getCode(), allErrors);

        return logMetricPipelineDto;
    }
}
