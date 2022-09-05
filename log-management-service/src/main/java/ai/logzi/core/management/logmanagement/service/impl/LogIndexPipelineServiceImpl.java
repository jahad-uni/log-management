package ai.logzi.core.management.logmanagement.service.impl;


import ai.logzi.core.management.logmanagement.service.LogIndexPipelineService;
import ai.logzi.core.management.logmanagement.service.constant.I18Constant;
import ai.logzi.core.management.logmanagement.service.exception.LogIndexNameListValidationException;
import ai.logzi.core.management.logmanagement.service.exception.LogPipelineNotFoundException;
import ai.logzi.core.management.logmanagement.service.dto.logIndex.LogIndexDto;
import ai.logzi.core.management.logmanagement.service.dto.logIndex.LogIndexOrderDto;
import ai.logzi.core.management.logmanagement.service.mapper.LogIndexPipelineDtoMapper;
import ai.logzi.core.management.logmanagement.service.validation.LogIndexPipelineValidation;
import ai.logzi.core.microservice.logmanagement.entity.logIndex.LogIndexEntity;
import ai.logzi.core.microservice.logmanagement.repository.LogIndexPipelineRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class LogIndexPipelineServiceImpl implements LogIndexPipelineService {
    private final LogIndexPipelineRepository logIndexPipelineRepository;
    private final LogIndexPipelineDtoMapper logIndexPipelineDtoMapper;
    private final LogIndexPipelineValidation logIndexPipelineValidation;

    @Override
    public LogIndexDto createLogIndex(String tenantId, LogIndexDto logIndexDto) throws Exception {

        var validatedLogIndexPipelineDto = logIndexPipelineValidation
                .validateLogIndex(logIndexDto);

        var logIndexEntity = logIndexPipelineDtoMapper
                .toLogIndexEntity(tenantId, validatedLogIndexPipelineDto);

        logIndexEntity.setCreatedBy(validatedLogIndexPipelineDto.getUserId());
        logIndexEntity.setCreatedAt(LocalDateTime.now());
        logIndexEntity.setUpdatedAt(LocalDateTime.now());
        logIndexEntity.setUpdatedBy(validatedLogIndexPipelineDto.getUserId());
        var order = logIndexPipelineRepository.countByTenantId(tenantId) + 1L;
        logIndexEntity.setOrder(order);

        logIndexEntity = logIndexPipelineRepository.save(logIndexEntity);

        return logIndexPipelineDtoMapper.fromLogIndexEntity(logIndexEntity);
    }

    @Override
    public List<LogIndexDto> getAllLogIndexesOrderByOrder(String tenantId) {
        var logPipelineEntityListFromDB = this.logIndexPipelineRepository
                .findAllByTenantIdOrderByOrder(tenantId);

        return logIndexPipelineDtoMapper.fromLogIndexEntityList(logPipelineEntityListFromDB);
    }

    @Override
    public List<LogIndexDto> getAllLogIndexOrderByName(String tenantId, String name) {
        var logIndexEntityListFromDB = this.logIndexPipelineRepository
                .findAllByTenantIdAndNameOrderByName(tenantId, name);

        return logIndexPipelineDtoMapper.fromLogIndexEntityList(logIndexEntityListFromDB);
    }

    @Override
    public LogIndexDto getLogIndex(String tenantId, String name) {
        var logIndexEntity = this.loadLogIndex(tenantId, name);

        return logIndexPipelineDtoMapper.fromLogIndexEntity(logIndexEntity);
    }


    @Override
    public LogIndexDto updateLogIndex(String tenantId, LogIndexDto logIndexDto) throws Exception {

        var validatedLogIndexDto = logIndexPipelineValidation
                .validateLogIndex(logIndexDto);

        var logIndexEntity = this.loadLogIndex(tenantId, validatedLogIndexDto.getName());
        validatedLogIndexDto.setId(logIndexEntity.getId());
        logIndexPipelineDtoMapper.toExistedLogIndexEntity(
                validatedLogIndexDto,
                logIndexEntity);

        logIndexEntity.setUpdatedBy(logIndexDto.getUserId());
        logIndexEntity.setUpdatedAt(LocalDateTime.now());
        logIndexEntity = this.logIndexPipelineRepository.save(logIndexEntity);

        return logIndexPipelineDtoMapper.fromLogIndexEntity(logIndexEntity);
    }

    @Override
    public List<String> getIndexNameListOrderByOrder(String tenantId) {
        return logIndexPipelineRepository
                .findAllNamesByTenantIdOrderByOrder(tenantId);
    }

    @Override
    public LogIndexOrderDto updateLogIndexesOrder(String tenantId, LogIndexOrderDto logIndexOrderDto) {
        var logIndexEntities = logIndexPipelineRepository
                .findAllByTenantIdOrderByOrder(tenantId);
        // Check Count of All Entities in DB With IndexNames Count
        if (logIndexEntities.size() != logIndexOrderDto.getIndex_names().size())
            throw new LogIndexNameListValidationException(
                    I18Constant.LOG_INDEX_ORDER_NAME_LIST_NOT_COMPLETE.getCode());

        var entityList = new ArrayList<LogIndexEntity>();
        for (String indexName : logIndexOrderDto.getIndex_names()) {
            var entity = logIndexEntities.stream()
                    .filter(f -> f.getName().equals(indexName))
                    .findAny().orElseThrow();
            // Calculate new Order
            var order = (long) logIndexOrderDto.getIndex_names().indexOf(indexName) + 1;
            if (entity.getOrder() != order) {
                entity.setUpdatedAt(LocalDateTime.now());
                entity.setUpdatedBy(logIndexOrderDto.getUserId());
                entity.setOrder(order);
                entityList.add(entity);
            }
        }

        logIndexPipelineRepository.saveAll(entityList);
        return logIndexOrderDto;
    }


    private LogIndexEntity loadLogIndex(final String tenantId,
                                        final String name) {

        return logIndexPipelineRepository
                .findOneByTenantIdAndName(tenantId, name)
                .orElseThrow(() -> new LogPipelineNotFoundException(
                        I18Constant.LOG_INDEX_NOT_FOUND.getCode(),
                        name));
    }


}
