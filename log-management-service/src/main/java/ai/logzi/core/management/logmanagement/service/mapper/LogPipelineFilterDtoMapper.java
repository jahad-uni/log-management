package ai.logzi.core.management.logmanagement.service.mapper;

import ai.logzi.core.management.logmanagement.service.dto.log.processor.model.LogPipelineFilterDto;
import ai.logzi.core.microservice.logmanagement.repository.model.LogPipelineFilter;
import org.mapstruct.Mapper;

@Mapper
public interface LogPipelineFilterDtoMapper {

    LogPipelineFilter toLogPipelineFilter(LogPipelineFilterDto logPipelineFilterDto);

}
