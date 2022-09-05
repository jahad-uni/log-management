package ai.logzi.core.management.logmanagement.service.impl;


import ai.logzi.core.management.logmanagement.service.LogMetricPipelineService;
import ai.logzi.core.management.logmanagement.service.constant.I18Constant;
import ai.logzi.core.management.logmanagement.service.dto.logMetric.LogMetricPipelineDto;
import ai.logzi.core.management.logmanagement.service.exception.LogPipelineNotFoundException;
import ai.logzi.core.management.logmanagement.service.mapper.LogMetricPipelineDtoMapper;
import ai.logzi.core.management.logmanagement.service.validation.LogMetricPipelineValidation;
import ai.logzi.core.microservice.logmanagement.entity.logMetric.LogMetricPipelineEntity;
import ai.logzi.core.microservice.logmanagement.repository.LogMetricPipelineRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
@AllArgsConstructor
public class LogMetricPipelineServiceImpl implements LogMetricPipelineService {
    private final LogMetricPipelineRepository logMetricPipelineRepository;
    private final LogMetricPipelineDtoMapper logMetricPipelineDtoMapper;
    private final LogMetricPipelineValidation logMetricPipelineValidation;

    @Override
    public List<LogMetricPipelineDto> getAllLogMetrics(String tenantId) {
        var logMetricPipelineEntityListFromDB = this.logMetricPipelineRepository
                .findAllByTenantId(tenantId);
        return logMetricPipelineDtoMapper.fromLogMetricPipelineEntityList(logMetricPipelineEntityListFromDB);
    }

    @Override
    public LogMetricPipelineDto getLogMetric(String tenantId, String id) {
        var logMetricPipelineEntity = this.loadLogMetric(tenantId, id);

        return logMetricPipelineDtoMapper.fromLogMetricPipelineEntity(logMetricPipelineEntity);
    }

    @Override
    public LogMetricPipelineDto createLogMetric(String tenantId, LogMetricPipelineDto logMetricPipelineDto) throws Exception {
        var validatedLogMetricPipelineDto = logMetricPipelineValidation
                .validateLogMetric(logMetricPipelineDto);

        var logMetricEntity = logMetricPipelineDtoMapper
                .toLogMetricPipelineEntity(tenantId, validatedLogMetricPipelineDto);

        logMetricEntity.setCreatedBy(validatedLogMetricPipelineDto.getUserId());
        logMetricEntity.setCreatedAt(LocalDateTime.now());
        logMetricEntity.setUpdatedAt(LocalDateTime.now());
        logMetricEntity.setUpdatedBy(validatedLogMetricPipelineDto.getUserId());

        logMetricEntity = logMetricPipelineRepository.save(logMetricEntity);

        return logMetricPipelineDtoMapper.fromLogMetricPipelineEntity(logMetricEntity);
    }

    @Override
    public LogMetricPipelineDto updateLogMetric(String tenantId, String id,LogMetricPipelineDto logMetricPipelineDto) throws Exception {

        var validatedLogMetricPipelineDto = logMetricPipelineValidation
                .validateLogMetric(logMetricPipelineDto);

        var logMetricEntity = this.loadLogMetric(tenantId, id);
        validatedLogMetricPipelineDto.setId(logMetricEntity.getId());
        logMetricPipelineDtoMapper.toExistedLogMetricEntity(
                validatedLogMetricPipelineDto,
                logMetricEntity);

        logMetricEntity.setUpdatedBy(logMetricPipelineDto.getUserId());
        logMetricEntity.setUpdatedAt(LocalDateTime.now());
        logMetricEntity = this.logMetricPipelineRepository.save(logMetricEntity);

        return logMetricPipelineDtoMapper.fromLogMetricPipelineEntity(logMetricEntity);
    }


    @Override
    public void deleteLogMetric(String tenantId, String userId, String id) {
        var logMetricPipelineDtoList = this
                .getAllLogMetrics(tenantId);
        logMetricPipelineDtoList.removeIf(f -> f.getId().equals(id));

        this.logMetricPipelineRepository.deleteByTenantIdAndId(tenantId, id);
    }


    private LogMetricPipelineEntity loadLogMetric(final String tenantId,
                                                  final String id) {
        return logMetricPipelineRepository
                .findOneByTenantIdAndId(tenantId, id)
                .orElseThrow(() -> new LogPipelineNotFoundException(
                        I18Constant.LOG_METRIC_NOT_FOUND.getCode(),
                        id));
    }

}
