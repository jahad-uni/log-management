package ai.logzi.core.management.logmanagement.service.impl;

import ai.logzi.core.management.logmanagement.service.LogPipelineService;
import ai.logzi.core.management.logmanagement.service.dto.log.*;
import ai.logzi.core.management.logmanagement.service.dto.log.filter.LogPipelineFilterDto;
import ai.logzi.core.management.logmanagement.service.exception.LogPipelineException;
import ai.logzi.core.management.logmanagement.service.exception.LogPipelineIdListValidationException;
import ai.logzi.core.management.logmanagement.service.exception.LogPipelineNotFoundException;
import ai.logzi.core.management.logmanagement.service.mapper.LogPipelineDtoMapper;
import ai.logzi.core.management.logmanagement.service.mapper.LogPipelineFilterDtoMapper;
import ai.logzi.core.management.logmanagement.service.type.log.LogPipelineProcessorType;
import ai.logzi.core.management.logmanagement.service.validation.LogPipelineValidation;
import ai.logzi.core.management.logmanagement.service.constant.I18Constant;
import ai.logzi.core.management.logmanagement.service.constant.TenantConstant;
import ai.logzi.core.microservice.logmanagement.common.helper.StringHelper;
import ai.logzi.core.management.logmanagement.service.type.BooleanType;
import ai.logzi.core.management.logmanagement.service.type.LastUpdatedTimeType;
import ai.logzi.core.management.logmanagement.service.type.log.LogPipelineCoreFilterType;
import ai.logzi.core.management.logmanagement.service.type.log.LogPipelineType;
import ai.logzi.core.microservice.logmanagement.entity.log.LogPipelineEntity;
import ai.logzi.core.microservice.logmanagement.repository.LogPipelineRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LogPipelineServiceImpl implements LogPipelineService {

    private final LogPipelineRepository logPipelineRepository;
    private final LogPipelineDtoMapper logPipelineDtoMapper;
    private final LogPipelineFilterDtoMapper logPipelineFilterDtoMapper;
    private final LogPipelineValidation logPipelineValidation;
    private final StringHelper stringHelper;

    private LogPipelineEntity loadLogPipeline(final String tenantId,
                                              final String id) {

        return logPipelineRepository
                .findOneByTenantIdAndId(tenantId, id)
                .orElseThrow(() -> new LogPipelineNotFoundException(
                        I18Constant.LOG_PIPELINE_NOT_FOUND.getCode(),
                        id));
    }

    private LogPipelineEntity loadLogPipeline(final String id) {

        return this.logPipelineRepository.findById(id)
                .orElseThrow(() -> new LogPipelineNotFoundException(
                        I18Constant.LOG_PIPELINE_NOT_FOUND.getCode(),
                        id));
    }

    @Override
    public List<LogPipelineDto> getAllLogPipelinesOrderByOrder(final String tenantId) {

        var logPipelineEntityListFromDB = this.logPipelineRepository
                .findAllByTenantIdOrderByOrder(tenantId);

        return logPipelineDtoMapper.fromLogPipelineEntityList(logPipelineEntityListFromDB);
    }

    @Override
    public List<LogPipelineDto> getAllLogPipelinesOrderByName(final String tenantId,
                                                              final String type) {

        var logPipelineEntityListFromDB = this.logPipelineRepository
                .findAllByTenantIdAndTypeOrderByName(tenantId, type);

        return logPipelineDtoMapper.fromLogPipelineEntityList(logPipelineEntityListFromDB);
    }

    @Override
    public LogPipelineFilterDto generateLogPipelineFilterDto(final String search) {

        var logPipelineFilterDto = new LogPipelineFilterDto();
        if (search != null && !search.isEmpty()) {
            String[] keyVals = search.split("  ");
            for (String keyVal : keyVals) {
                String[] parts = keyVal.split(":");
                if (parts[0].equalsIgnoreCase(LogPipelineCoreFilterType.IS_ENABLED.getCode())) {
                    var values = stringHelper.decodeFilterValues(parts[1]);
                    for (var val : values) {
                        if (val.trim().equalsIgnoreCase(BooleanType.TRUE.getCode()))
                            logPipelineFilterDto.getIsEnabled().add(true);
                        else if (val.trim().equalsIgnoreCase(BooleanType.FALSE.getCode()))
                            logPipelineFilterDto.getIsEnabled().add(false);
                    }
                } else if (parts[0].equalsIgnoreCase("-".concat(LogPipelineCoreFilterType.IS_ENABLED.getCode()))) {
                    var _values = stringHelper.decodeFilterValues(parts[1]);
                    for (var val : _values) {
                        if (val.trim().equalsIgnoreCase(BooleanType.TRUE.getCode()))
                            logPipelineFilterDto.get_isEnabled().add(true);
                        else if (val.trim().equalsIgnoreCase(BooleanType.FALSE.getCode()))
                            logPipelineFilterDto.get_isEnabled().add(false);
                    }
                } else if (parts[0].equalsIgnoreCase(LogPipelineCoreFilterType.IS_EMPTY.getCode())) {
                    var values = stringHelper.decodeFilterValues(parts[1]);
                    for (var val : values) {
                        if (val.trim().equalsIgnoreCase(BooleanType.TRUE.getCode()))
                            logPipelineFilterDto.getIsEmpty().add(true);
                        else if (val.trim().equalsIgnoreCase(BooleanType.FALSE.getCode()))
                            logPipelineFilterDto.getIsEmpty().add(false);
                    }
                } else if (parts[0].equalsIgnoreCase("-".concat(LogPipelineCoreFilterType.IS_EMPTY.getCode()))) {
                    var _values = stringHelper.decodeFilterValues(parts[1]);
                    for (var val : _values) {
                        if (val.trim().equalsIgnoreCase(BooleanType.TRUE.getCode()))
                            logPipelineFilterDto.get_isEmpty().add(true);
                        else if (val.trim().equalsIgnoreCase(BooleanType.FALSE.getCode()))
                            logPipelineFilterDto.get_isEmpty().add(false);
                    }
                } else if (parts[0].equalsIgnoreCase(LogPipelineCoreFilterType.IS_READONLY.getCode())) {
                    var values = stringHelper.decodeFilterValues(parts[1]);
                    for (var val : values) {
                        if (val.trim().equalsIgnoreCase(BooleanType.TRUE.getCode()))
                            logPipelineFilterDto.getIsReadonly().add(true);
                        else if (val.trim().equalsIgnoreCase(BooleanType.FALSE.getCode()))
                            logPipelineFilterDto.getIsReadonly().add(false);
                    }
                } else if (parts[0].equalsIgnoreCase("-".concat(LogPipelineCoreFilterType.IS_READONLY.getCode()))) {
                    var _values = stringHelper.decodeFilterValues(parts[1]);
                    for (var val : _values) {
                        if (val.trim().equalsIgnoreCase(BooleanType.TRUE.getCode()))
                            logPipelineFilterDto.get_isReadonly().add(true);
                        else if (val.trim().equalsIgnoreCase(BooleanType.FALSE.getCode()))
                            logPipelineFilterDto.get_isReadonly().add(false);
                    }
                } else if (parts[0].equalsIgnoreCase(LogPipelineCoreFilterType.TYPE.getCode())) {
                    var values = stringHelper.decodeFilterValues(parts[1]);
                    logPipelineFilterDto.setTypes(values);
                } else if (parts[0].equalsIgnoreCase("-".concat(LogPipelineCoreFilterType.TYPE.getCode()))) {
                    var _values = stringHelper.decodeFilterValues(parts[1]);
                    logPipelineFilterDto.set_types(_values);
                } else if (parts[0].equalsIgnoreCase(LogPipelineCoreFilterType.LAST_UPDATED_TIME.getCode())) {
                    var values = stringHelper.decodeFilterValues(parts[1]);
                    logPipelineFilterDto.setLastUpdatedTimes(values);
                } else if (parts[0].equalsIgnoreCase("-".concat(LogPipelineCoreFilterType.LAST_UPDATED_TIME.getCode()))) {
                    var _values = stringHelper.decodeFilterValues(parts[1]);
                    logPipelineFilterDto.set_lastUpdatedTimes(_values);
                } else if (parts[0].equalsIgnoreCase(LogPipelineCoreFilterType.LAST_UPDATED_BY.getCode())) {
                    var values = stringHelper.decodeFilterValues(parts[1]);
                    logPipelineFilterDto.setUpdatedBys(values);
                } else if (parts[0].equalsIgnoreCase("-".concat(LogPipelineCoreFilterType.LAST_UPDATED_BY.getCode()))) {
                    var _values = stringHelper.decodeFilterValues(parts[1]);
                    logPipelineFilterDto.set_updatedBys(_values);
                } else if (parts[0].equalsIgnoreCase(LogPipelineCoreFilterType.CONTAINS.getCode())) {
                    var values = stringHelper.decodeFilterValues(parts[1]);
                    logPipelineFilterDto.setProcessorTypes(values);
                } else if (parts[0].equalsIgnoreCase("-".concat(LogPipelineCoreFilterType.CONTAINS.getCode()))) {
                    var _values = stringHelper.decodeFilterValues(parts[1]);
                    logPipelineFilterDto.set_processorTypes(_values);
                }
            }
        }
        return logPipelineFilterDto;
    }

    @Override
    public List<LogPipelineDto> getAllLogPipelinesOrderByOrder(final String tenantId,
                                                               final LogPipelineFilterDto logPipelineFilterDto) {

        var logPipelineFilter = this.logPipelineFilterDtoMapper
                .toLogPipelineFilter(logPipelineFilterDto);

        var now = LocalDateTime.now();
        if (logPipelineFilterDto.getLastUpdatedTimes() != null &&
                !logPipelineFilterDto.getLastUpdatedTimes().isEmpty()) {
            LocalDateTime from = null;
            for (var time : logPipelineFilterDto.getLastUpdatedTimes()) {
                if (time.equals(LastUpdatedTimeType.LAST_DAY.getCode())) {
                    from = now.minusHours(24);
                } else if (time.equals(LastUpdatedTimeType.LAST_THREE_DAYS.getCode())) {
                    from = now.minusHours(74);
                } else if (time.equals(LastUpdatedTimeType.LAST_WEEK.getCode())) {
                    from = now.minusDays(7);
                } else if (time.equals(LastUpdatedTimeType.LAST_MONTH.getCode())) {
                    from = now.minusMonths(1);
                } else if (time.equals(LastUpdatedTimeType.LAST_YEAR.getCode())) {
                    from = now.minusYears(1);
                }
            }
            logPipelineFilter.setFrom(from);
            logPipelineFilter.setTo(now);
        } else if (logPipelineFilterDto.get_lastUpdatedTimes() != null &&
                !logPipelineFilterDto.get_lastUpdatedTimes().isEmpty()) {
            LocalDateTime from = now.minusYears(1);
            LocalDateTime to = null;
            for (var time : logPipelineFilterDto.getLastUpdatedTimes()) {
                if (time.equals(LastUpdatedTimeType.LAST_DAY.getCode())) {
                    to = now.minusHours(24);
                } else if (time.equals(LastUpdatedTimeType.LAST_THREE_DAYS.getCode())) {
                    to = now.minusHours(74);
                } else if (time.equals(LastUpdatedTimeType.LAST_WEEK.getCode())) {
                    to = now.minusDays(7);
                } else if (time.equals(LastUpdatedTimeType.LAST_MONTH.getCode())) {
                    to = now.minusMonths(1);
                } else if (time.equals(LastUpdatedTimeType.LAST_YEAR.getCode())) {
                    to = now.minusYears(1);
                    from = null;
                }
            }
            logPipelineFilter.setFrom(from);
            logPipelineFilter.setTo(to);
        } else {
            var from = now.minusYears(1);
            logPipelineFilter.setFrom(from);
            logPipelineFilter.setTo(now);
        }

        var logPipelineEntityList = this.logPipelineRepository
                .findAllLogPipelinesByFiltersOrderByOrder(tenantId, logPipelineFilter);

        return logPipelineDtoMapper.fromLogPipelineEntityList(logPipelineEntityList);
    }

    @Override
    public LogPipelineDto getLogPipeline(final String tenantId,
                                         final String id) {

        var logPipelineEntity = this.loadLogPipeline(tenantId, id);

        return logPipelineDtoMapper.fromLogPipelineEntity(logPipelineEntity);
    }

    @Override
    public LogPipelineDto createLogPipeline(final String tenantId,
                                            final LogPipelineDto logPipelineDto) throws Exception {

        var validatedLogPipelineDto = logPipelineValidation
                .validateLogPipeline(logPipelineDto);

        var logPipelineEntity = logPipelineDtoMapper
                .toLogPipelineEntity(tenantId, validatedLogPipelineDto);

        logPipelineEntity.setCreatedBy(validatedLogPipelineDto.getUserId());
        logPipelineEntity.setCreatedAt(LocalDateTime.now());
        logPipelineEntity.setUpdatedAt(LocalDateTime.now());
        logPipelineEntity.setUpdatedBy(validatedLogPipelineDto.getUserId());
        var order = logPipelineRepository.countByTenantId(tenantId) + 1;
        logPipelineEntity.setOrder(order);

        var processors = logPipelineEntity.getProcessors();
        for (var processor : processors) {
            processor.setId(UUID.randomUUID().toString());
            if (processor.getType().equals(LogPipelineProcessorType.PIPELINE.getCode())) {
                var nestedProcessors = processor.getNestedPipelineProcessor().getProcessors();
                for (var nestedProcessor : nestedProcessors) {
                    nestedProcessor.setId(UUID.randomUUID().toString());
                }
            }
        }
        logPipelineEntity = logPipelineRepository.save(logPipelineEntity);

        return logPipelineDtoMapper.fromLogPipelineEntity(logPipelineEntity);
    }

    @Override
    public LogPipelineDto cloneLogPipeline(final String tenantId,
                                           final String userId,
                                           final String id) throws Exception {

        var logPipelineEntityToClone = this.loadLogPipeline(id);
        boolean isLibrary = logPipelineEntityToClone.getTenantId().equals(TenantConstant.SHARED.getCode());
        if (!logPipelineEntityToClone.getTenantId().equals(tenantId) &&
                !isLibrary)
            throw new LogPipelineException(I18Constant.OPERATION_NOT_VALID.getCode());

        logPipelineEntityToClone.setId(null);
        logPipelineEntityToClone.setType(LogPipelineType.CUSTOM.getCode());

        if (isLibrary)
            logPipelineEntityToClone.set_enabled(true);
        else
            logPipelineEntityToClone.set_enabled(false);

        var logPipelineDtoToClone = this.logPipelineDtoMapper
                .fromLogPipelineEntity(logPipelineEntityToClone);

        var newLogPipelineDto = this.createLogPipeline(tenantId, logPipelineDtoToClone);

        if (isLibrary)
            return newLogPipelineDto;

        var logPipelineIdList = this.logPipelineRepository
                .findAllIdsByTenantIdOrderByOrder(tenantId);

        var newIdList = new ArrayList<String>();
        for (var logPipelineId : logPipelineIdList) {
            if (logPipelineId.equals(id)) {
                newIdList.add(newLogPipelineDto.getId());
                newIdList.add(logPipelineId);
            } else {
                if (!logPipelineId.equals(newLogPipelineDto.getId()))
                    newIdList.add(logPipelineId);
            }
        }
        this.updateLogPipelinesOrder(tenantId,
                new LogPipelineOrderDto(userId, newIdList));

        return newLogPipelineDto;
    }

    @Override
    public LogPipelineDto updateLogPipeline(final String tenantId,
                                            final LogPipelineDto logPipelineDto) throws Exception {

        var validatedLogPipelineDto = logPipelineValidation
                .validateLogPipeline(logPipelineDto);

        var logPipelineEntity = this.loadLogPipeline(tenantId, validatedLogPipelineDto.getId());

        logPipelineDtoMapper.toExistedLogPipelineEntity(
                validatedLogPipelineDto,
                logPipelineEntity);

        logPipelineEntity.setUpdatedBy(logPipelineDto.getUserId());
        logPipelineEntity.setUpdatedAt(LocalDateTime.now());

        logPipelineEntity = this.logPipelineRepository.save(logPipelineEntity);

        return logPipelineDtoMapper.fromLogPipelineEntity(logPipelineEntity);
    }

    @Transactional
    @Override
    public List<LogPipelineDto> updateLogPipelines(final String tenantId,
                                                   final List<LogPipelineDto> logPipelineDtos,
                                                   final String deletedId) throws Exception {

        // Check DtoList has only 1 or 2 Object
        if (logPipelineDtos.size() == 0 || logPipelineDtos.size() > 2)
            throw new LogPipelineException(I18Constant.UPDATE_ONLY_ONE_OR_TWO_PIPELINE.getCode());
        var updatedLogPipelineDtoList = new ArrayList<LogPipelineDto>();
        for (var logPipelineDto : logPipelineDtos) {
            // Check deletedId not in DtoList
            if (logPipelineDto.getId().equals(deletedId))
                throw new LogPipelineException(I18Constant.DELETED_PIPELINE_CAN_NOT_UPDATE.getCode());
            // Update Pipeline
            updatedLogPipelineDtoList.add(this.updateLogPipeline(tenantId, logPipelineDto));
        }
        // Delete deletedId if exists
        if (deletedId != null && !deletedId.isEmpty())
            this.deleteLogPipeline(tenantId,
                    logPipelineDtos.stream().findFirst().get().getUserId(),
                    deletedId);
        return updatedLogPipelineDtoList;
    }

    @Transactional
    @Override
    public LogPipelineOrderDto updateLogPipelinesOrder(final String tenantId,
                                                       final LogPipelineOrderDto logPipelineOrderDto) {

        // todo : refactor with better approach and do it with out load all pipeline fields and Do it Atomic with
        //  just update order in mongo

        var logPipelineEntities = logPipelineRepository
                .findAllByTenantIdOrderByOrder(tenantId);
        // Check Count of All Entities in DB With PipelineIds Count
        if (logPipelineEntities.size() != logPipelineOrderDto.getPipeline_ids().size())
            throw new LogPipelineIdListValidationException(
                    I18Constant.LOG_PIPELINE_ORDER_ID_LIST_NOT_COMPLETE.getCode());

        var entityList = new ArrayList<LogPipelineEntity>();
        for (String pipelineId : logPipelineOrderDto.getPipeline_ids()) {
            var entity = logPipelineEntities.stream()
                    .filter(f -> f.getId().equals(pipelineId))
                    .findAny().orElseThrow();
            // Calculate new Order
            var order = logPipelineOrderDto.getPipeline_ids().indexOf(pipelineId) + 1;
            if (entity.getOrder() != order) {
                entity.setUpdatedAt(LocalDateTime.now());
                entity.setUpdatedBy(logPipelineOrderDto.getUserId());
                entity.setOrder(order);
                entityList.add(entity);
            }
        }

        logPipelineRepository.saveAll(entityList);
        return logPipelineOrderDto;
    }

    @Override
    public List<String> getLogPipelineIdListOrderByOrder(final String tenantId) {

        return logPipelineRepository
                .findAllIdsByTenantIdOrderByOrder(tenantId);
    }

    @Override
    @Transactional
    public void deleteLogPipeline(final String tenantId,
                                  final String userId,
                                  final String id) {

        var logPipelineDtoList = this
                .getAllLogPipelinesOrderByOrder(tenantId);
        logPipelineDtoList.removeIf(f -> f.getId().equals(id));

        this.logPipelineRepository.deleteByTenantIdAndId(tenantId, id);

        var pipelineIds = logPipelineDtoList
                .stream()
                .map(LogPipelineDto::getId)
                .collect(Collectors.toList());

        this.updateLogPipelinesOrder(tenantId,
                new LogPipelineOrderDto(userId, pipelineIds));
    }

    @Override
    public void deleteAllLogPipelines(final String tenantId) {

        this.logPipelineRepository.deleteAllByTenantId(tenantId);
    }

    public List<String> getUpdatedByIdsDistinct(final String tenantId) {

        return this.logPipelineRepository
                .findAllUpdatedByIdsDistinctByUpdatedBy(tenantId);

    }
}
