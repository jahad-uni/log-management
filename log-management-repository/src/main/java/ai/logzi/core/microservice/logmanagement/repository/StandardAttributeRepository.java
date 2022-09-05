package ai.logzi.core.microservice.logmanagement.repository;

import ai.logzi.core.microservice.logmanagement.entity.attribute.StandardAttributeEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StandardAttributeRepository extends MongoRepository<StandardAttributeEntity, String>, LogPipelineRepositoryCustom {


    Optional<StandardAttributeEntity> findOneByTenantIdAndId(final String tenantId,
                                                             final String id);

    List<StandardAttributeEntity> findAllByTenantIdOrderByOrder(final String tenantId);

    List<StandardAttributeEntity> findAllByTenantIdAndTypeOrderByAttribute(final String tenantId,
                                                                      final String type);

    List<StandardAttributeEntity> findAllByTenantIdAndIdInOrderByOrder(final String tenantId,
                                                                       final List<String> ids);

    void deleteByTenantIdAndId(final String tenantId,
                               final String id);

    void deleteAllByTenantId(final String tenantId);

    Long countByTenantId(final String tenantId);

    @Query(value = "{'name' : {$in: ?0},", sort = "{ order : 1 }")
    List<StandardAttributeEntity> findAllStandardAttributesByFiltersOrderByOrder(
            final String filter
    );


}
