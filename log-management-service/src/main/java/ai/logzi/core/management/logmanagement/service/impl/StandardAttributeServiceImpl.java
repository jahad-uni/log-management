package ai.logzi.core.management.logmanagement.service.impl;

import ai.logzi.core.management.logmanagement.service.StandardAttributeService;
import ai.logzi.core.management.logmanagement.service.constant.I18Constant;
import ai.logzi.core.management.logmanagement.service.exception.LogPipelineIdListValidationException;
import ai.logzi.core.management.logmanagement.service.exception.LogPipelineNotFoundException;
import ai.logzi.core.management.logmanagement.service.dto.standardAttribute.StandardAttributeDto;
import ai.logzi.core.management.logmanagement.service.dto.standardAttribute.StandardAttributeOrderDto;
import ai.logzi.core.management.logmanagement.service.mapper.StandardAttributeDtoMapper;
import ai.logzi.core.management.logmanagement.service.validation.StandardAttributeValidation;
import ai.logzi.core.microservice.logmanagement.entity.attribute.StandardAttributeEntity;
import ai.logzi.core.microservice.logmanagement.repository.StandardAttributeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StandardAttributeServiceImpl implements StandardAttributeService {
    private final StandardAttributeRepository standardAttributeRepository;
    private final StandardAttributeDtoMapper standardAttributeDtoMapper;
    private final StandardAttributeValidation standardAttributeValidation;

    @Override
    public List<StandardAttributeDto> getAllStandardAttributesOrderByOrder(String tenantId) {
        var standardAttributeEntityListFromDB = this.standardAttributeRepository
                .findAllByTenantIdOrderByOrder(tenantId);

        return standardAttributeDtoMapper.fromStandardAttributeEntityList(standardAttributeEntityListFromDB);
    }

    @Override
    public List<StandardAttributeDto> getAllStandardAttributesOrderByName(String tenantId, String attribute) {
        return null;
    }


    @Override
    public StandardAttributeDto getStandardAttribute(String tenantId, String id) {
        var standardAttributeEntity = this.loadStandardAttribute(tenantId, id);

        return standardAttributeDtoMapper.fromStandardAttributeEntity(standardAttributeEntity);
    }

    @Override
    public StandardAttributeDto createStandardAttribute(String tenantId, StandardAttributeDto standardAttributeDto) throws Exception {

        var validatedStandardAttributeDto = standardAttributeValidation
                .validateStandardAttribute(standardAttributeDto);

        var standardAttributeEntity = standardAttributeDtoMapper
                .toStandardAttributeEntity(tenantId, validatedStandardAttributeDto);

        standardAttributeEntity.setCreatedBy(validatedStandardAttributeDto.getUserId());
        standardAttributeEntity.setCreatedAt(LocalDateTime.now());
        standardAttributeEntity.setUpdatedAt(LocalDateTime.now());
        standardAttributeEntity.setUpdatedBy(validatedStandardAttributeDto.getUserId());
        var order = standardAttributeRepository.countByTenantId(tenantId) + 1L;
        standardAttributeEntity.setOrder(order);

        standardAttributeEntity = standardAttributeRepository.save(standardAttributeEntity);

        return standardAttributeDtoMapper.fromStandardAttributeEntity(standardAttributeEntity);
    }

    @Override
    public StandardAttributeDto updateStandardAttribute(String tenantId, StandardAttributeDto standardAttributeDto) throws Exception {
        var validatedStandardAttributeDto = standardAttributeValidation
                .validateStandardAttribute(standardAttributeDto);

        var standardAttributeEntity = this.loadStandardAttribute(tenantId, validatedStandardAttributeDto.getId());

        standardAttributeDtoMapper.toExistedStandardAttributeEntity(
                validatedStandardAttributeDto,
                standardAttributeEntity);

        standardAttributeEntity.setUpdatedBy(standardAttributeDto.getUserId());
        standardAttributeEntity.setUpdatedAt(LocalDateTime.now());

        standardAttributeEntity = this.standardAttributeRepository.save(standardAttributeEntity);

        return standardAttributeDtoMapper.fromStandardAttributeEntity(standardAttributeEntity);
    }

    @Override
    public List<StandardAttributeDto> updateStandardAttribute(String tenantId, List<StandardAttributeDto> standardAttributeDtoList, String deletedId) throws Exception {
        return null;
    }

    @Override
    public void updateStandardAttributesOrder(String tenantId, StandardAttributeOrderDto standardAttributeOrderDto) {
        var standardAttributeEntities = standardAttributeRepository
                .findAllByTenantIdOrderByOrder(tenantId);
        // Check Count of All Entities in DB With Ids Count
        if (standardAttributeEntities.size() != standardAttributeOrderDto.getIds().size())
            throw new LogPipelineIdListValidationException(
                    I18Constant.STANDARD_ATTRIBUTE_ORDER_ID_LIST_NOT_COMPLETE.getCode());

        var entityList = new ArrayList<StandardAttributeEntity>();
        for (String standardAttributeId : standardAttributeOrderDto.getIds()) {
            var entity = standardAttributeEntities.stream()
                    .filter(f -> f.getId().equals(standardAttributeId))
                    .findAny().orElseThrow();
            // Calculate new Order
            var order = (long) standardAttributeOrderDto.getIds().indexOf(standardAttributeId) + 1;
            if (entity.getOrder() != order) {
                entity.setUpdatedAt(LocalDateTime.now());
                entity.setUpdatedBy(standardAttributeOrderDto.getUserId());
                entity.setOrder(order);
                entityList.add(entity);
            }
        }

        standardAttributeRepository.saveAll(entityList);
    }


    @Override
    public void deleteStandardAttribute(String tenantId, String userId, String id) {
        var standardAttributeDtoList = this
                .getAllStandardAttributesOrderByOrder(tenantId);
        standardAttributeDtoList.removeIf(f -> f.getId().equals(id));

        this.standardAttributeRepository.deleteByTenantIdAndId(tenantId, id);

        var standardAttributeIds = standardAttributeDtoList
                .stream()
                .map(StandardAttributeDto::getId)
                .collect(Collectors.toList());

        this.updateStandardAttributesOrder(tenantId,
                new StandardAttributeOrderDto(userId, standardAttributeIds));
    }

    @Override
    public void deleteAllStandardAttributes(String tenantId) {

    }

    private StandardAttributeEntity loadStandardAttribute(final String tenantId,
                                                          final String id) {

        return standardAttributeRepository
                .findOneByTenantIdAndId(tenantId, id)
                .orElseThrow(() -> new LogPipelineNotFoundException(
                        I18Constant.STANDARD_ATTRIBUTE_NOT_FOUND.getCode(),
                        id));
    }
}
