package ai.logzi.core.microservice.logmanagement.repository;

import java.util.List;

public interface LogIndexRepositoryCustom {


    List<String> findAllNamesByTenantIdOrderByOrder(final String tenantId);



}
