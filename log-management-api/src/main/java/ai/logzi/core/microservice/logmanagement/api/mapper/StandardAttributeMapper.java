package ai.logzi.core.microservice.logmanagement.api.mapper;

import ai.logzi.core.management.logmanagement.service.dto.standardAttribute.StandardAttributeDto;
import ai.logzi.core.management.logmanagement.service.dto.standardAttribute.StandardAttributeOrderDto;
import ai.logzi.core.microservice.logmanagement.api.model.standardAttribute.StandardAttribute;
import ai.logzi.core.microservice.logmanagement.api.model.standardAttribute.StandardAttributeInput;
import ai.logzi.core.microservice.logmanagement.api.model.standardAttribute.StandardAttributeOrder;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface StandardAttributeMapper {
    StandardAttributeDto toStandardAttributeDto(String tenantId,
                                                String userId,
                                                StandardAttributeInput standardAttributeInput);

    StandardAttributeDto toStandardAttributeDto(String tenantId,
                                                String userId,
                                                String id,
                                                StandardAttributeInput standardAttributeInput);

    List<StandardAttributeDto> toStandardAttributeList(List<StandardAttribute> standardAttributes);

    StandardAttribute fromStandardAttributeDto(StandardAttributeDto standardAttributeDto);

    List<StandardAttribute> fromStandardAttributeDtoList(List<StandardAttributeDto> standardAttributeDtoList);

    StandardAttributeOrderDto toStandardAttributeOrderDto(String tenantId,
                                                          String userId,
                                                          StandardAttributeOrder standardAttributeOrder);

    StandardAttribute fromStandardAttributeOrderDto(StandardAttributeOrderDto standardAttributeOrderDto);
}
