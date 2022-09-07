package ai.logzi.core.microservice.logmanagement.repository.impl;

import ai.logzi.core.microservice.logmanagement.entity.log.LogPipelineEntity;
import ai.logzi.core.microservice.logmanagement.repository.LogPipelineRepositoryCustom;
import ai.logzi.core.microservice.logmanagement.repository.model.LogPipelineFilter;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Repository
public class LogPipelineRepositoryCustomImpl implements LogPipelineRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    @Override
    public List<String> findAllUpdatedByIdsDistinctByUpdatedBy(final String tenantId) {

        Query query = new Query();
        query.addCriteria(Criteria.where("tenantId").is(tenantId));
        return mongoTemplate.findDistinct(
                query,
                "updatedBy",
                LogPipelineEntity.class,
                String.class);
    }

    @Override
    public List<String> findAllIdsByTenantIdOrderByOrder(String tenantId) {

        Query query = new Query();
        query.addCriteria(Criteria.where("tenantId").is(tenantId));
        query.with(Sort.by(Sort.Direction.ASC, "order"));
        query.fields().include("_id");
        return mongoTemplate
                .find(query, LogPipelineEntity.class)
                .stream().map(LogPipelineEntity::getId).collect(Collectors.toList());
    }

    @Override
    public List<LogPipelineEntity> findAllLogPipelinesByFiltersOrderByOrder(String tenantId,
                                                                            LogPipelineFilter logPipelineFilter) {

        var result = new ArrayList<LogPipelineEntity>();
        Query query = new Query();
        Query query1 = new Query();
        query.addCriteria(Criteria.where("updatedAt")
                .gte(logPipelineFilter.getFrom())
                .lte(logPipelineFilter.getTo()));
        query.addCriteria(Criteria.where("tenantId").is(tenantId));
        query1.addCriteria(Criteria.where("updatedAt")
                .gte(logPipelineFilter.getFrom())
                .lte(logPipelineFilter.getTo()));
        query1.addCriteria(Criteria.where("tenantId").is(tenantId));
        var hasQuery1 = false;
        if (logPipelineFilter.get_isEmpty() != null &&
                !logPipelineFilter.get_isEmpty().isEmpty()) {

            if (logPipelineFilter.get_isEmpty().size() > 1)
                return result;
            else if (logPipelineFilter.get_isEmpty().get(0).equals(true)) {
                if (logPipelineFilter.getProcessorTypes() != null &&
                        !logPipelineFilter.getProcessorTypes().isEmpty())
                    query.addCriteria(Criteria.where("processors.type")
                            .in(logPipelineFilter.getProcessorTypes()));
                else if (logPipelineFilter.get_processorTypes() != null &&
                        !logPipelineFilter.get_processorTypes().isEmpty())
                    query.addCriteria(Criteria.where("processors.type")
                            .nin(logPipelineFilter.get_processorTypes()));
            } else if (logPipelineFilter.get_isEmpty().get(0).equals(false)) {
                query.addCriteria(Criteria.where("processors").size(0));
            }
        } else if (logPipelineFilter.getIsEmpty() != null &&
                !logPipelineFilter.getIsEmpty().isEmpty()) {

            if (logPipelineFilter.getIsEmpty().size() > 2)
                return result;
            else if (logPipelineFilter.getIsEmpty().size() == 2) {
                if (logPipelineFilter.getProcessorTypes() != null &&
                        !logPipelineFilter.getProcessorTypes().isEmpty()) {
                    query.addCriteria(Criteria.where("processors.type")
                            .in(logPipelineFilter.getProcessorTypes()));
                    query1.addCriteria(Criteria.where("processors").size(0));
                    hasQuery1 = true;
                } else if (logPipelineFilter.get_processorTypes() != null &&
                        !logPipelineFilter.get_processorTypes().isEmpty()) {
                    query.addCriteria(Criteria.where("processors.type")
                            .nin(logPipelineFilter.get_processorTypes()));
                }
            } else if (logPipelineFilter.getIsEmpty().get(0).equals(true)) {
                query.addCriteria(Criteria.where("processors").size(0));
            } else if (logPipelineFilter.getIsEmpty().get(0).equals(false)) {

                if (logPipelineFilter.getProcessorTypes() != null &&
                        !logPipelineFilter.getProcessorTypes().isEmpty())
                    query.addCriteria(Criteria.where("processors.type")
                            .in(logPipelineFilter.getProcessorTypes()));
                else if (logPipelineFilter.get_processorTypes() != null &&
                        !logPipelineFilter.get_processorTypes().isEmpty())
                    query.addCriteria(Criteria.where("processors.type")
                            .nin(logPipelineFilter.get_processorTypes()));
            }
        } else {
            if (logPipelineFilter.getProcessorTypes() != null &&
                    !logPipelineFilter.getProcessorTypes().isEmpty()) {
                query.addCriteria(Criteria.where("processors.type")
                        .in(logPipelineFilter.getProcessorTypes()));
                query1.addCriteria(Criteria.where("processors").size(0));
                hasQuery1 = true;
            }
            else if (logPipelineFilter.get_processorTypes() != null &&
                    !logPipelineFilter.get_processorTypes().isEmpty()) {
                query.addCriteria(Criteria.where("processors.type")
                        .nin(logPipelineFilter.get_processorTypes()));
            }
        }

        this.addFilter(query, logPipelineFilter);
        var queryResult = this.mongoTemplate
                .find(query, LogPipelineEntity.class);
        result.addAll(queryResult);
        if (hasQuery1) {
            this.addFilter(query1, logPipelineFilter);
            var query1Result = this.mongoTemplate
                    .find(query1, LogPipelineEntity.class);
            result.addAll(query1Result);
        }
        return result.stream()
                .sorted(Comparator.comparing(LogPipelineEntity::getOrder))
                .collect(Collectors.toList());
    }


    private void addFilter(Query query, LogPipelineFilter logPipelineFilter) {

        if (logPipelineFilter.getIsEnabled() != null &&
                !logPipelineFilter.getIsEnabled().isEmpty())
            query.addCriteria(Criteria.where("is_enabled")
                    .in(logPipelineFilter.getIsEnabled()));
        else if (logPipelineFilter.get_isEnabled() != null &&
                !logPipelineFilter.get_isEnabled().isEmpty())
            query.addCriteria(Criteria.where("is_enabled")
                    .nin(logPipelineFilter.get_isEnabled()));
        if (logPipelineFilter.getIsReadonly() != null &&
                !logPipelineFilter.getIsReadonly().isEmpty())
            query.addCriteria(Criteria.where("is_readonly")
                    .in(logPipelineFilter.getIsReadonly()));
        else if (logPipelineFilter.get_isReadonly() != null &&
                !logPipelineFilter.get_isReadonly().isEmpty())
            query.addCriteria(Criteria.where("is_readonly")
                    .nin(logPipelineFilter.get_isReadonly()));
        if (logPipelineFilter.get_types() != null &&
                !logPipelineFilter.get_types().isEmpty())
            query.addCriteria(Criteria.where("type")
                    .nin(logPipelineFilter.get_types()));
        else if (logPipelineFilter.getUpdatedBys() != null &&
                !logPipelineFilter.getUpdatedBys().isEmpty())
            query.addCriteria(Criteria.where("updatedBy")
                    .in(logPipelineFilter.getUpdatedBys()));
        if (logPipelineFilter.get_updatedBys() != null &&
                !logPipelineFilter.get_updatedBys().isEmpty())
            query.addCriteria(Criteria.where("updatedBy")
                    .nin(logPipelineFilter.get_updatedBys()));
    }
}
