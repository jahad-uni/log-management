package ai.logzi.core.management.logmanagement.service.validation.util;

import ai.logzi.core.management.logmanagement.service.constant.I18Constant;
import ai.logzi.core.management.logmanagement.service.dto.standardAttribute.StandardAttributeDto;
import ai.logzi.core.management.logmanagement.service.type.standardAttribute.StandardAttributeType;
import ai.logzi.core.management.logmanagement.service.validation.dto.FieldErrorDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Component
public class StandardAttributeValidationUtil {
    public List<FieldErrorDto> validateStandardAttribute(final StandardAttributeDto standardAttributeDto) {
        var fieldErrorDtos = new ArrayList<FieldErrorDto>();
        // Check Standard Attribute, Attribute Not Empty
        if (standardAttributeDto.getAttribute() == null || standardAttributeDto.getAttribute().isEmpty()) {
            var error = new FieldErrorDto("attribute",
                    I18Constant.STANDARD_ATTRIBUTE_ATTRIBUTE_ABSENT.getCode());
            fieldErrorDtos.add(error);
        }
        // Check Standard Attribute, type Not Empty
        if (standardAttributeDto.getType() == null || standardAttributeDto.getType().isEmpty()) {
            var error = new FieldErrorDto("type",
                    I18Constant.STANDARD_ATTRIBUTE_ATTRIBUTE_TYPE_ABSENT.getCode());
            fieldErrorDtos.add(error);
        } else {

            AtomicBoolean validType = new AtomicBoolean(false);
            new ArrayList<>(EnumSet.allOf(StandardAttributeType.class))
                    .forEach(f -> {
                        if ((standardAttributeDto.getType().equals(f.getCode()))) {
                            validType.set(true);
                        }

                    });
            if (!validType.get()) {
                var error = new FieldErrorDto("type",
                        I18Constant.STANDARD_ATTRIBUTE_TYPE_INVALID.getCode());
                fieldErrorDtos.add(error);
            }
        }
        return fieldErrorDtos;
    }
}



