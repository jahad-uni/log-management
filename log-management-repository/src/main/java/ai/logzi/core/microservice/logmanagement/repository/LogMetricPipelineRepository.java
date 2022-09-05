package ai.logzi.core.microservice.logmanagement.repository;

import ai.logzi.core.microservice.logmanagement.entity.logMetric.LogMetricPipelineEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LogMetricPipelineRepository extends MongoRepository<LogMetricPipelineEntity, String> {

    List<LogMetricPipelineEntity> findAllByTenantId(final String tenantId);

    Optional<LogMetricPipelineEntity> findOneByTenantIdAndId(final String tenantId,
                                                      final String id);

    void deleteByTenantIdAndId(final String tenantId,
                               final String id);
}
