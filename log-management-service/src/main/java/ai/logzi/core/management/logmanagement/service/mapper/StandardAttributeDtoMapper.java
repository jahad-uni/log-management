package ai.logzi.core.management.logmanagement.service.mapper;

import ai.logzi.core.management.logmanagement.service.dto.standardAttribute.StandardAttributeDto;
import ai.logzi.core.microservice.logmanagement.entity.attribute.StandardAttributeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper
public interface StandardAttributeDtoMapper {

    void toExistedStandardAttributeEntity(StandardAttributeDto standardAttributeDto,
                                          @MappingTarget StandardAttributeEntity existedStandardAttributeEntity);

    StandardAttributeEntity toStandardAttributeEntity(String tenantId, StandardAttributeDto standardAttributeDto);

    StandardAttributeDto fromStandardAttributeEntity(StandardAttributeEntity standardAttributeEntity);

    List<StandardAttributeDto> fromStandardAttributeEntityList(List<StandardAttributeEntity> standardAttributeEntityList);


}
