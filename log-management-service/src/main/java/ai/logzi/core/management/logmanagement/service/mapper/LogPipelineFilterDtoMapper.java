package ai.logzi.core.management.logmanagement.service.mapper;

import ai.logzi.core.management.logmanagement.service.dto.log.filter.LogPipelineFilterDto;
import ai.logzi.core.microservice.logmanagement.repository.filter.LogPipelineFilter;
import org.mapstruct.Mapper;

@Mapper
public interface LogPipelineFilterDtoMapper {

    LogPipelineFilter toLogPipelineFilter(LogPipelineFilterDto logPipelineFilterDto);

    LogPipelineFilterDto fromLogPipelineFilter(LogPipelineFilter logPipelineFilter);

}
