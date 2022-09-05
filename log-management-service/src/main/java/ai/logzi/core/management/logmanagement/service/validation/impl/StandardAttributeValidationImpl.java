package ai.logzi.core.management.logmanagement.service.validation.impl;

import ai.logzi.core.management.logmanagement.service.constant.I18Constant;
import ai.logzi.core.management.logmanagement.service.exception.LogPipelineValidationException;
import ai.logzi.core.management.logmanagement.service.dto.standardAttribute.StandardAttributeDto;
import ai.logzi.core.management.logmanagement.service.validation.StandardAttributeValidation;
import ai.logzi.core.management.logmanagement.service.validation.util.StandardAttributeValidationUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Getter
public class StandardAttributeValidationImpl implements StandardAttributeValidation {
    StandardAttributeValidationUtil standardAttributeValidationUtil;

    @Override
    public StandardAttributeDto validateStandardAttribute(StandardAttributeDto standardAttributeDto) throws Exception {

        var allErrors = this.standardAttributeValidationUtil
                .validateStandardAttribute(standardAttributeDto);

        if (!allErrors.isEmpty())
            throw new LogPipelineValidationException(I18Constant.LOG_PIPELINE_VALIDATION_ERROR.getCode(), allErrors);

        return standardAttributeDto;
    }
}
