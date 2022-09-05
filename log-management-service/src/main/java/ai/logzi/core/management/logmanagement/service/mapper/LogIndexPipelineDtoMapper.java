package ai.logzi.core.management.logmanagement.service.mapper;

import ai.logzi.core.management.logmanagement.service.dto.logIndex.LogIndexDto;
import ai.logzi.core.microservice.logmanagement.entity.logIndex.LogIndexEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper
public interface LogIndexPipelineDtoMapper {

    void toExistedLogIndexEntity(LogIndexDto logIndexDto,
                                 @MappingTarget LogIndexEntity existedLogIndexEntity);

    LogIndexEntity toLogIndexEntity(String tenantId, LogIndexDto logIndexDto);

    LogIndexDto fromLogIndexEntity(LogIndexEntity logIndexEntity);

    List<LogIndexDto> fromLogIndexEntityList(List<LogIndexEntity> logIndexEntities);


}
