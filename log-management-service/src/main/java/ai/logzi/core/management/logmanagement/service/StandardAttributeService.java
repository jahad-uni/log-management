package ai.logzi.core.management.logmanagement.service;

import ai.logzi.core.management.logmanagement.service.dto.standardAttribute.StandardAttributeDto;
import ai.logzi.core.management.logmanagement.service.dto.standardAttribute.StandardAttributeOrderDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StandardAttributeService {

    List<StandardAttributeDto> getAllStandardAttributesOrderByOrder(final String tenantId);

    List<StandardAttributeDto> getAllStandardAttributesOrderByName(final String tenantId,
                                                                   final String attribute);

    StandardAttributeDto getStandardAttribute(final String tenantId,
                                              final String id);

    StandardAttributeDto createStandardAttribute(final String tenantId,
                                                 final StandardAttributeDto standardAttributeDto) throws Exception;


    StandardAttributeDto updateStandardAttribute(final String tenantId,
                                                 final StandardAttributeDto standardAttributeDto) throws Exception;

    List<StandardAttributeDto> updateStandardAttribute(final String tenantId,
                                                       final List<StandardAttributeDto> standardAttributeDtoList,
                                                       final String deletedId) throws Exception;

    void updateStandardAttributesOrder(final String tenantId,
                                       final StandardAttributeOrderDto StandardAttributeOrderDto);


    void deleteStandardAttribute(final String tenantId,
                                 final String userId,
                                 final String id);

    void deleteAllStandardAttributes(final String tenantId);

}
