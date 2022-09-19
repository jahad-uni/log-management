package ai.logzi.core.microservice.logmanagement.api.mapper;


import ai.logzi.core.management.logmanagement.service.dto.log.LogPipelineDto;
import ai.logzi.core.management.logmanagement.service.dto.log.LogPipelineProcessorDto;
import ai.logzi.core.management.logmanagement.service.dto.log.processor.GrokParserProcessorDto;
import ai.logzi.core.microservice.logmanagement.api.model.log.LogPipeline;
import ai.logzi.core.microservice.logmanagement.api.model.log.LogPipelineProcessor;
import ai.logzi.core.microservice.logmanagement.api.model.log.processors.GrokParserProcessor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface LogPipelineMapper {

    @Mapping(target="emailBox", source = "logPipeline.email")
//    @Mapping(target="startDt", source="dto.employeeStartDt",
//            dateFormat="dd-MM-yyyy HH:mm:ss")
    LogPipelineDto toLogPipeLineDto(String tenantId,
                                    String userId,
                                    LogPipeline logPipeline);
    @Mapping(target="updatedTime", source = "logPipeline.updatedAt")
    LogPipelineDto toLogPipeLineDto(String tenantId,
                                    String userId,
                                    String id,
                                    LogPipeline logPipeline);


    LogPipelineDto toLogPipeLineDto(LogPipeline logPipeline);

//    GrokParserProcessorDto toGrokParserProcessorDto(GrokParserProcessor grokParserProcessor);

    LogPipelineProcessorDto toLogPipeLineProcessorDto(LogPipelineProcessor pipelineProcessor);

    List<LogPipelineDto> toLogPipeLineDtoList(List<LogPipeline> logPipelines);

    LogPipeline fromLogPipelineDto(LogPipelineDto logPipelineDto);

    List<LogPipeline> fromLogPipelineDtoList(List<LogPipelineDto> logPipelineDtoList);


}
