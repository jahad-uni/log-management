package ai.logzi.core.microservice.logmanagement.repository.impl;

import ai.logzi.core.microservice.logmanagement.entity.logIndex.LogIndexEntity;
import ai.logzi.core.microservice.logmanagement.repository.LogIndexRepositoryCustom;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Repository
public class LogIndexRepositoryCustomImpl implements LogIndexRepositoryCustom {

    private final MongoTemplate mongoTemplate;


    @Override
    public List<String> findAllNamesByTenantIdOrderByOrder(String tenantId) {

        Query query = new Query();
        query.addCriteria(Criteria.where("tenantId").is(tenantId));
        query.with(Sort.by(Sort.Direction.ASC, "order"));

        return mongoTemplate
                .find(query, LogIndexEntity.class)
                .stream().map(LogIndexEntity::getName).collect(Collectors.toList());
    }


}
