package ai.logzi.core.microservice.logmanagement.api.mapper;


import ai.logzi.core.management.logmanagement.service.dto.logIndex.LogIndexDto;
import ai.logzi.core.management.logmanagement.service.dto.logIndex.LogIndexOrderDto;
import ai.logzi.core.microservice.logmanagement.api.model.logIndex.LogIndex;
import ai.logzi.core.microservice.logmanagement.api.model.logIndex.LogIndexInput;
import ai.logzi.core.microservice.logmanagement.api.model.logIndex.LogIndexOrder;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface LogIndexPipelineMapper {

    LogIndexDto toLogIndexDto(String tenantId,
                              String userId,
                              LogIndexInput logIndexInput);

    LogIndexDto toLogIndexDto(String tenantId,
                              String userId,
                              String name,
                              LogIndexInput logIndexInput);

    List<LogIndexDto> toLogIndexDtoList(List<LogIndex> logIndices);

    LogIndex fromLogIndexDto(LogIndexDto logIndexDto);

    List<LogIndex> fromLogIndexDtoList(List<LogIndexDto> logIndexDtoList);

    LogIndexOrderDto toLogIndexOrderDto(String tenantId,
                                           String userId,
                                           LogIndexOrder logIndexOrder);

    LogIndexOrder fromLogIndexOrderDto(LogIndexOrderDto logIndexOrderDto);
}
