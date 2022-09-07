package ai.logzi.core.microservice.logmanagement.repository;

import ai.logzi.core.microservice.logmanagement.entity.log.LogPipelineEntity;
import ai.logzi.core.microservice.logmanagement.repository.model.LogPipelineFilter;

import java.util.List;

public interface LogPipelineRepositoryCustom {

    List<String> findAllUpdatedByIdsDistinctByUpdatedBy(final String tenantId);

    List<String> findAllIdsByTenantIdOrderByOrder(final String tenantId);

    List<LogPipelineEntity> findAllLogPipelinesByFiltersOrderByOrder(String tenantId,
                                                                     LogPipelineFilter logPipelineFilter);


}
