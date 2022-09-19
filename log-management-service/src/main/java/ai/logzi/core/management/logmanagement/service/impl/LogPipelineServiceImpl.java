package ai.logzi.core.management.logmanagement.service.impl;

import ai.logzi.core.management.logmanagement.service.LogPipelineService;
import ai.logzi.core.microservice.logmanagement.common.constant.I18Constant;
import ai.logzi.core.management.logmanagement.service.dto.log.LogPipelineDto;
import ai.logzi.core.management.logmanagement.service.dto.log.processor.model.LogPipelineFilterDto;
import ai.logzi.core.management.logmanagement.service.exception.LogPipelineNotFoundException;
import ai.logzi.core.management.logmanagement.service.mapper.LogPipelineDtoMapper;
import ai.logzi.core.management.logmanagement.service.mapper.LogPipelineFilterDtoMapper;
import ai.logzi.core.management.logmanagement.service.type.BooleanType;
import ai.logzi.core.management.logmanagement.service.type.LastUpdatedTimeType;
import ai.logzi.core.management.logmanagement.service.type.log.LogPipelineCoreFilterType;
import ai.logzi.core.management.logmanagement.service.type.log.LogPipelineProcessorType;
import ai.logzi.core.management.logmanagement.service.validation.LogPipelineValidation;
import ai.logzi.core.microservice.logmanagement.common.helper.StringHelper;
import ai.logzi.core.microservice.logmanagement.entity.log.LogPipelineEntity;
import ai.logzi.core.microservice.logmanagement.repository.LogPipelineRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

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
                .orElseThrow(() -> new LogPipelineNotFoundException(id));
    }

    @Override
    public List<LogPipelineDto> getAllLogPipelines(final String tenantId) {

        var logPipelineEntityListFromDB = this.logPipelineRepository
                .findAllByTenantIdOrderByOrder(tenantId);

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
    public List<LogPipelineDto> getAllLogPipelines(final String tenantId,
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
                .toLogPipelineEntity(tenantId, logPipelineDto);

        logPipelineEntity.setCreatedBy(logPipelineDto.getUserId());
        logPipelineEntity.setCreatedAt(LocalDateTime.now());
        logPipelineEntity.setUpdatedAt(LocalDateTime.now());
        logPipelineEntity.setUpdatedBy(logPipelineDto.getUserId());
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


    @Override
    @Transactional
    public void deleteLogPipeline(final String tenantId,
                                  final String userId,
                                  final String id) {

        var logPipelineDtoList = this
                .getAllLogPipelines(tenantId);
        logPipelineDtoList.removeIf(f -> f.getId().equals(id));

        this.logPipelineRepository.deleteByTenantIdAndId(tenantId, id);

    }

    @Override
    public void deleteAllLogPipelines(final String tenantId) {

        this.logPipelineRepository.deleteAllByTenantId(tenantId);
    }

    @Override
    public String getEmailAddress(String email) {
        return email;
    }

    public List<String> getUpdatedByIdsDistinct(final String tenantId) {

        return this.logPipelineRepository
                .findAllUpdatedByIdsDistinctByUpdatedBy(tenantId);

    }
}
