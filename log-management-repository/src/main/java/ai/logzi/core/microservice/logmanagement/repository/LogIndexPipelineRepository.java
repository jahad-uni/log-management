package ai.logzi.core.microservice.logmanagement.repository;

import ai.logzi.core.microservice.logmanagement.entity.logIndex.LogIndexEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LogIndexPipelineRepository extends MongoRepository<LogIndexEntity, String>,LogIndexRepositoryCustom{

    Long countByTenantId(final String tenantId);

    List<LogIndexEntity> findAllByTenantIdOrderByOrder(final String tenantId);

    List<LogIndexEntity> findAllByTenantIdAndNameOrderByName(final String tenantId,
                                                                final String name);

    Optional<LogIndexEntity> findOneByTenantIdAndName(final String tenantId,
                                                       final String name);



}
