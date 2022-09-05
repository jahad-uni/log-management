package ai.logzi.core.management.logmanagement.service.validation;

import ai.logzi.core.management.logmanagement.service.dto.standardAttribute.StandardAttributeDto;

public interface StandardAttributeValidation {
    StandardAttributeDto validateStandardAttribute(final StandardAttributeDto standardAttributeDto) throws Exception;
}
