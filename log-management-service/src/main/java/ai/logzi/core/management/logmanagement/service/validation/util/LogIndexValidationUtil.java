package ai.logzi.core.management.logmanagement.service.validation.util;

import ai.logzi.core.management.logmanagement.service.constant.I18Constant;
import ai.logzi.core.management.logmanagement.service.dto.logIndex.ExclusionFilterDto;
import ai.logzi.core.management.logmanagement.service.dto.logIndex.LogIndexDto;
import ai.logzi.core.management.logmanagement.service.type.logIndex.ExclusionFilterSampleRateType;
import ai.logzi.core.management.logmanagement.service.validation.dto.FieldErrorDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LogIndexValidationUtil {
    public List<FieldErrorDto> validateLogIndex(final LogIndexDto logIndexDto) {
        var fieldErrorDtos = new ArrayList<FieldErrorDto>();
        // Check LogIndex name Not Empty
        if (logIndexDto.getName() == null || logIndexDto.getName().isEmpty()) {
            var error = new FieldErrorDto("name",
                    I18Constant.LOG_INDEX_NAME_IS_EMPTY.getCode());
            fieldErrorDtos.add(error);
        }

        // Check LogIndex filter Not Empty
        if (logIndexDto.getFilter() == null) {
            var error = new FieldErrorDto("filter",
                    I18Constant.LOG_INDEX_FILTER_IS_EMPTY.getCode());
            fieldErrorDtos.add(error);
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

        }else if (exclusionFilterDto.getFilter() != null ){
            if (!exclusionFilterDto.getFilter().getSample_rate_type().equals(ExclusionFilterSampleRateType.ALL_LOGS.getCode()) &&
                    !exclusionFilterDto.getFilter().getSample_rate_type().equals(ExclusionFilterSampleRateType.TRACE_ID.getCode())){
                FieldErrorDto error = new FieldErrorDto("exclusionFilter[" + index + "].filter.sampleRateType",
                        I18Constant.LOG_INDEX_EXCLUSION_FILTER_FILTER_SAMPLE_RATE_TYPE_INVALID.getCode());
                fieldErrorDtos.add(error);
            }

        }
            return fieldErrorDtos;
    }


}
