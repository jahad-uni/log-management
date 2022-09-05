package ai.logzi.core.management.logmanagement.service.validation.util;

import ai.logzi.core.management.logmanagement.service.constant.I18Constant;
import ai.logzi.core.management.logmanagement.service.dto.logIndex.ExclusionFilterDto;
import ai.logzi.core.management.logmanagement.service.dto.logMetric.LogMetricPipelineDto;
import ai.logzi.core.management.logmanagement.service.type.logIndex.ExclusionFilterSampleRateType;
import ai.logzi.core.management.logmanagement.service.type.logMetric.ComputeAggregationType;
import ai.logzi.core.management.logmanagement.service.type.logMetric.LogMetricType;
import ai.logzi.core.management.logmanagement.service.validation.dto.FieldErrorDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Component
public class LogMetricPipeLineValidationUtil {
    public List<FieldErrorDto> validateLogMetric(final LogMetricPipelineDto logMetricPipelineDto) {
        var fieldErrorDtos = new ArrayList<FieldErrorDto>();
        // Check LogMetric name Not Empty
        if (logMetricPipelineDto.getName() == null || logMetricPipelineDto.getName().isEmpty()) {
            var error = new FieldErrorDto("name",
                    I18Constant.LOG_METRIC_NAME_IS_EMPTY.getCode());
            fieldErrorDtos.add(error);
        }

        // Check LogMetric, type Not Empty
        if (logMetricPipelineDto.getType() == null || logMetricPipelineDto.getType().isEmpty()) {
            var error = new FieldErrorDto("type",
                    I18Constant.LOG_METRIC_TYPE_IS_EMPTY.getCode());
            fieldErrorDtos.add(error);
        } else {

            AtomicBoolean validType = new AtomicBoolean(false);
            new ArrayList<>(EnumSet.allOf(LogMetricType.class))
                    .forEach(f -> {
                        if ((logMetricPipelineDto.getType().equals(f.getCode()))) {
                            validType.set(true);
                        }

                    });
            if (!validType.get()) {
                var error = new FieldErrorDto("type",
                        I18Constant.LOG_METRIC_TYPE_INVALID.getCode());
                fieldErrorDtos.add(error);
            }
        }


        // Check LogMetric, aggregation_type Not Empty
        if (logMetricPipelineDto.getAttributes().getCompute().getAggregation_type() == null) {
            var error = new FieldErrorDto("aggregation_type ",
                    I18Constant.LOG_METRIC_ATTRIBUTE_AGGREGATION_TYPE_IS_EMPTY.getCode());
            fieldErrorDtos.add(error);
        } else {

            AtomicBoolean validType = new AtomicBoolean(false);
            new ArrayList<>(EnumSet.allOf(ComputeAggregationType.class))
                    .forEach(f -> {
                        if ((logMetricPipelineDto.getAttributes().getCompute().getAggregation_type().equals(f.getCode()))) {
                            validType.set(true);
                        }

                    });
            if (!validType.get()) {
                var error = new FieldErrorDto("aggregation_type ",
                        I18Constant.LOG_METRIC_ATTRIBUTE_AGGREGATION_TYPE_INVALID.getCode());
                fieldErrorDtos.add(error);
            }
        }
        return fieldErrorDtos;
    }


    public List<FieldErrorDto> validateLogIndexExclusionFilter(final ExclusionFilterDto exclusionFilterDto,
                                                               final String index) {
        var fieldErrorDtos = new ArrayList<FieldErrorDto>();
        // Check exclusion filter name Not Empty
        if (exclusionFilterDto.getName() == null || exclusionFilterDto.getName().isEmpty()) {
            FieldErrorDto error = new FieldErrorDto("exclusionFilter[" + index + "].name",
                    I18Constant.LOG_INDEX_EXCLUSION_FILTER_NAME_IS_EMPTY.getCode());
            fieldErrorDtos.add(error);

        } else if (exclusionFilterDto.getFilter() != null) {
            if (!exclusionFilterDto.getFilter().getSample_rate_type().equals(ExclusionFilterSampleRateType.ALL_LOGS.getCode()) &&
                    !exclusionFilterDto.getFilter().getSample_rate_type().equals(ExclusionFilterSampleRateType.TRACE_ID.getCode())) {
                FieldErrorDto error = new FieldErrorDto("exclusionFilter[" + index + "].filter.sampleRateType",
                        I18Constant.LOG_INDEX_EXCLUSION_FILTER_FILTER_SAMPLE_RATE_TYPE_INVALID.getCode());
                fieldErrorDtos.add(error);
            }

        }
        return fieldErrorDtos;
    }


}
