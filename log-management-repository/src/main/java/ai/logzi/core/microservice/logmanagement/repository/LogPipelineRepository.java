package ai.logzi.core.microservice.logmanagement.repository;

import ai.logzi.core.microservice.logmanagement.entity.log.LogPipelineEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LogPipelineRepository extends MongoRepository<LogPipelineEntity, String> , LogPipelineRepositoryCustom {


    Optional<LogPipelineEntity> findOneByTenantIdAndId(final String tenantId,
                                                       final String id);

    List<LogPipelineEntity> findAllByTenantIdOrderByOrder(final String tenantId);

    List<LogPipelineEntity> findAllByTenantIdAndTypeOrderByName(final String tenantId,
                                                                 final String type);

    List<LogPipelineEntity> findAllByTenantIdAndIdInOrderByOrder(final String tenantId,
                                                                 final List<String> ids);

    void deleteByTenantIdAndId(final String tenantId,
                               final String id);

    void deleteAllByTenantId(final String tenantId);

    int countByTenantId(final String tenantId);

    @Query(value = "{'is_enabled' : {$in: ?0}," +
            "'is_readonly' : {$in: ?1}, " +
            "'type' : {$in: ?2}, " +
            "'updatedBy' : {$in: ?3}, " +
            "'processors.type' : {$in: ?4} }", sort = "{ order : 1 }")
    List<LogPipelineEntity> findAllLogPipelinesByFiltersOrderByOrder(
            final List<Boolean> isEnabled,
            final List<Boolean> isReadOnly,
            final List<String> types,
            final List<String> updatedBys,
            final List<String> processorTypes);

    
}
