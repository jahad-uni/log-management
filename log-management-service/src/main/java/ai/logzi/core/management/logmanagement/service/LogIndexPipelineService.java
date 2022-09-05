package ai.logzi.core.management.logmanagement.service;


import ai.logzi.core.management.logmanagement.service.dto.logIndex.LogIndexDto;
import ai.logzi.core.management.logmanagement.service.dto.logIndex.LogIndexOrderDto;

import java.util.List;

public interface LogIndexPipelineService {

    LogIndexDto createLogIndex(final String tenantId,
                               final LogIndexDto logIndexDto) throws Exception;

    List<LogIndexDto> getAllLogIndexesOrderByOrder(final String tenantId);

    List<LogIndexDto> getAllLogIndexOrderByName(final String tenantId,
                                                final String name);

    LogIndexDto getLogIndex(final String tenantId,
                            final String name);


    LogIndexDto updateLogIndex(final String tenantId,
                               final LogIndexDto logIndexDto) throws Exception;


    List<String> getIndexNameListOrderByOrder(final String tenantId);

    LogIndexOrderDto updateLogIndexesOrder(final String tenantId,
                                           final LogIndexOrderDto logIndexOrderDto);


}
