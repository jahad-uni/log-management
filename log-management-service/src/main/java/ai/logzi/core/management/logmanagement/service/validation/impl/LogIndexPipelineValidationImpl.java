package ai.logzi.core.management.logmanagement.service.validation.impl;

import ai.logzi.core.management.logmanagement.service.constant.I18Constant;
import ai.logzi.core.management.logmanagement.service.exception.LogPipelineValidationException;
import ai.logzi.core.management.logmanagement.service.dto.logIndex.LogIndexDto;
import ai.logzi.core.management.logmanagement.service.validation.LogIndexPipelineValidation;
import ai.logzi.core.management.logmanagement.service.validation.util.LogIndexValidationUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Getter
public class LogIndexPipelineValidationImpl implements LogIndexPipelineValidation {

    private final LogIndexValidationUtil logIndexValidationUtil;

    @Override
    public LogIndexDto validateLogIndex(LogIndexDto logIndexDto) throws Exception {

        var allErrors = this.logIndexValidationUtil
                .validateLogIndex(logIndexDto);

        // Check LogIndex Exclusion filter
        for (var exclusionFilterDto : logIndexDto.getExclusion_filters()) {
            exclusionFilterDto.setIndex(String.valueOf(logIndexDto.getExclusion_filters().indexOf(exclusionFilterDto)));
            var validatedLogIndexExclusionFilterDto =
                    logIndexValidationUtil.validateLogIndexExclusionFilter(exclusionFilterDto, exclusionFilterDto.getIndex());
            allErrors.addAll(validatedLogIndexExclusionFilterDto);
        }

        if (!allErrors.isEmpty())
            throw new LogPipelineValidationException(I18Constant.LOG_INDEX_VALIDATION_ERROR.getCode(), allErrors);

        return logIndexDto;
    }
}
