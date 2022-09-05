package ai.logzi.core.microservice.logmanagement.api.controller;

import ai.logzi.core.management.logmanagement.service.constant.I18Constant;
import ai.logzi.core.management.logmanagement.service.dto.standardAttribute.StandardAttributeDto;
import ai.logzi.core.management.logmanagement.service.StandardAttributeService;
import ai.logzi.core.management.logmanagement.service.type.standardAttribute.StandardAttributeType;
import ai.logzi.core.microservice.logmanagement.api.mapper.StandardAttributeMapper;
import ai.logzi.core.microservice.logmanagement.api.model.Category;
import ai.logzi.core.microservice.logmanagement.api.model.CategoryElement;
import ai.logzi.core.microservice.logmanagement.api.model.SuccessResponse;
import ai.logzi.core.microservice.logmanagement.api.model.standardAttribute.StandardAttribute;
import ai.logzi.core.microservice.logmanagement.api.model.standardAttribute.StandardAttributeInput;
import ai.logzi.core.microservice.logmanagement.common.helper.LocaleHelper;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

@Tag(name = "Standard Attribute Api",
        description = "These Api used to deal with standard attribute")
@RestController
@RequestMapping("/api/v1/tenant/{tenantId}/logs/config/")
@AllArgsConstructor
public class StandardAttributeController {
    private final StandardAttributeService standardAttributeService;
    private final StandardAttributeMapper standardAttributeMapper;
    private final LocaleHelper localeHelper;

    @GetMapping("/standardattributes")
    public ResponseEntity<SuccessResponse<List<StandardAttribute>>>
    getAllStandardAttribute(@PathVariable final String tenantId,
                            @RequestHeader("user_id") final String userId,
                            @RequestParam final String filter) {

        List<StandardAttribute> standardAttributeList;
        List<StandardAttributeDto> standardAttributeDtoList;
        if (filter != null && !filter.isEmpty()) {

            standardAttributeDtoList = this.standardAttributeService
                    .getAllStandardAttributesOrderByName(tenantId, filter);
        } else {
            standardAttributeDtoList = this.standardAttributeService
                    .getAllStandardAttributesOrderByOrder(tenantId);
        }

        standardAttributeList = this.standardAttributeMapper
                .fromStandardAttributeDtoList(standardAttributeDtoList);

        return ResponseEntity.ok(new SuccessResponse<>(standardAttributeList,
                HttpStatus.OK.value(),
                localeHelper.getLocalMessage(I18Constant.READ_SUCCESSFULLY.getCode())));
    }


    @GetMapping("/standardattributes/{id}")
    public ResponseEntity<SuccessResponse<StandardAttribute>>
    getStandardAttribute(@PathVariable final String tenantId,
                         @RequestHeader("user_id") final String userId,
                         @PathVariable final String id) {

        var standardAttributeDto = this.standardAttributeService
                .getStandardAttribute(tenantId, id);

        var standardAttributeApiModel = this.standardAttributeMapper
                .fromStandardAttributeDto(standardAttributeDto);

        return ResponseEntity.ok(new SuccessResponse<>(standardAttributeApiModel,
                HttpStatus.OK.value(),
                localeHelper.getLocalMessage(I18Constant.READ_SUCCESSFULLY.getCode())));
    }


    @PostMapping("/standardattrbutes")
    public ResponseEntity<SuccessResponse<StandardAttribute>>
    createStandardAttribute(@PathVariable final String tenantId,
                            @RequestHeader("user_id") final String userId,
                            @RequestBody @Valid final StandardAttributeInput standardAttributeInput) throws Exception {

        var standardAttributeDto = this.standardAttributeMapper
                .toStandardAttributeDto(tenantId, userId, standardAttributeInput);

        standardAttributeDto = this.standardAttributeService
                .createStandardAttribute(tenantId, standardAttributeDto);

        var standardAttributeApiModel = this.standardAttributeMapper
                .fromStandardAttributeDto(standardAttributeDto);

        return ResponseEntity.ok(new SuccessResponse<>(standardAttributeApiModel,
                HttpStatus.OK.value(),
                localeHelper.getLocalMessage(I18Constant.CREATED_SUCCESSFULLY.getCode())));
    }


    @PutMapping("/standardattributes/{id}")
    public ResponseEntity<SuccessResponse<StandardAttribute>>
    updateStandardAttribute(@PathVariable final String tenantId,
                            @RequestHeader("user_id") final String userId,
                            @PathVariable final String id,
                            @RequestBody final StandardAttributeInput standardAttributeInput) throws Exception {

        var standardAttributeDto = this.standardAttributeMapper
                .toStandardAttributeDto(tenantId, userId, id, standardAttributeInput);

        standardAttributeDto = this.standardAttributeService
                .updateStandardAttribute(tenantId, standardAttributeDto);

        var standardAttributeApiModel = standardAttributeMapper
                .fromStandardAttributeDto(standardAttributeDto);

        return ResponseEntity.ok(new SuccessResponse<>(standardAttributeApiModel,
                HttpStatus.OK.value(),
                localeHelper.getLocalMessage(I18Constant.UPDATED_SUCCESSFULLY.getCode())));
    }


    @DeleteMapping("/standardattributes/{id}")
    public ResponseEntity<SuccessResponse<Object>>
    deleteStandardAttribute(@PathVariable final String tenantId,
                            @RequestHeader("user_id") final String userId,
                            @PathVariable final String id) {

        this.standardAttributeService
                .deleteStandardAttribute(tenantId, userId, id);

        return ResponseEntity.ok(new SuccessResponse<>(null,
                HttpStatus.OK.value(),
                localeHelper.getLocalMessage(I18Constant.DELETED_SUCCESSFULLY.getCode())));
    }


    @GetMapping("/standardattributes/attribute-type")
    public ResponseEntity<SuccessResponse<Category>>
    getStandardAttributeType(@PathVariable final String tenantId,
                              @RequestHeader("user_id") final String userId) {

        var categoryApiModel = new Category();
        categoryApiModel.setCode("");
        new ArrayList<>(EnumSet.allOf(StandardAttributeType.class))
                .forEach(f -> {
                    categoryApiModel.getCategoryElements().add(CategoryElement
                            .builder()
                            .code(f.getCode())
                            .build());
                });

        return ResponseEntity.ok(new SuccessResponse<>(categoryApiModel,
                HttpStatus.OK.value(),
                localeHelper.getLocalMessage(I18Constant.READ_SUCCESSFULLY.getCode())));
    }


}
