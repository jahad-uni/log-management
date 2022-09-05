package ai.logzi.core.microservice.logmanagement.api.controller;

import ai.logzi.core.management.logmanagement.service.LogIndexPipelineService;
import ai.logzi.core.management.logmanagement.service.constant.I18Constant;
import ai.logzi.core.management.logmanagement.service.dto.logIndex.LogIndexDto;
import ai.logzi.core.management.logmanagement.service.type.logIndex.ExclusionFilterSampleRateType;
import ai.logzi.core.microservice.logmanagement.api.mapper.LogIndexPipelineMapper;
import ai.logzi.core.microservice.logmanagement.api.model.Category;
import ai.logzi.core.microservice.logmanagement.api.model.CategoryElement;
import ai.logzi.core.microservice.logmanagement.api.model.SuccessResponse;
import ai.logzi.core.microservice.logmanagement.api.model.logIndex.LogIndex;
import ai.logzi.core.microservice.logmanagement.api.model.logIndex.LogIndexInput;
import ai.logzi.core.microservice.logmanagement.api.model.logIndex.LogIndexOrder;
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

@Tag(name = "Log Index Api",
        description = "These Api used to deal with log indexes")
@RestController
@RequestMapping("/api/v1/tenant/{tenantId}/logs/config/")
@AllArgsConstructor
public class LogIndexPipelineController {
    private final LogIndexPipelineService logIndexPipelineService;
    private final LogIndexPipelineMapper logIndexPipelineMapper;
    private final LocaleHelper localeHelper;


    @PostMapping("/indexes")
    public ResponseEntity<SuccessResponse<LogIndex>>
    createLogIndex(@PathVariable final String tenantId,
                   @RequestHeader("user_id") final String userId,
                   @RequestBody @Valid final LogIndexInput logIndexInput) throws Exception {

        var logPipelineDto = this.logIndexPipelineMapper
                .toLogIndexDto(tenantId, userId, logIndexInput);

        logPipelineDto = this.logIndexPipelineService
                .createLogIndex(tenantId, logPipelineDto);

        var LogIndexApiModel = this.logIndexPipelineMapper
                .fromLogIndexDto(logPipelineDto);

        return ResponseEntity.ok(new SuccessResponse<>(LogIndexApiModel,
                HttpStatus.OK.value(),
                localeHelper.getLocalMessage(I18Constant.CREATED_SUCCESSFULLY.getCode())));
    }


    @GetMapping("/indexes")
    public ResponseEntity<SuccessResponse<List<LogIndex>>>
    getAllLogIndexes(@PathVariable final String tenantId,
                     @RequestHeader("user_id") final String userId,
                     @RequestParam final String filter) {

        List<LogIndex> logIndexList;
        List<LogIndexDto> logIndexDtoList;
        if (filter != null && !filter.isEmpty()) {
            logIndexDtoList = this.logIndexPipelineService
                    .getAllLogIndexOrderByName(tenantId, filter);
        } else {
            logIndexDtoList = this.logIndexPipelineService
                    .getAllLogIndexesOrderByOrder(tenantId);
        }

        logIndexList = this.logIndexPipelineMapper
                .fromLogIndexDtoList(logIndexDtoList);

        return ResponseEntity.ok(new SuccessResponse<>(logIndexList,
                HttpStatus.OK.value(),
                localeHelper.getLocalMessage(I18Constant.READ_SUCCESSFULLY.getCode())));

    }


    @GetMapping("/index/{name}")
    public ResponseEntity<SuccessResponse<LogIndex>>
    getLogIndex(@PathVariable final String tenantId,
                @RequestHeader("user_id") final String userId,
                @PathVariable final String name) {

        var logPipelineDto = this.logIndexPipelineService
                .getLogIndex(tenantId, name);

        var logIndexApiModel = this.logIndexPipelineMapper
                .fromLogIndexDto(logPipelineDto);

        return ResponseEntity.ok(new SuccessResponse<>(logIndexApiModel,
                HttpStatus.OK.value(),
                localeHelper.getLocalMessage(I18Constant.READ_SUCCESSFULLY.getCode())));
    }


    @PutMapping("/indexes/{name}")
    public ResponseEntity<SuccessResponse<LogIndex>>
    updateLogIndex(@PathVariable final String tenantId,
                   @RequestHeader("user_id") final String userId,
                   @PathVariable final String name,
                   @RequestBody final LogIndexInput logIndexInput) throws Exception {

        var logIndexDto = this.logIndexPipelineMapper
                .toLogIndexDto(tenantId, userId, name, logIndexInput);

        logIndexDto = this.logIndexPipelineService
                .updateLogIndex(tenantId, logIndexDto);

        var logIndexApiModel = logIndexPipelineMapper
                .fromLogIndexDto(logIndexDto);

        return ResponseEntity.ok(new SuccessResponse<>(logIndexApiModel,
                HttpStatus.OK.value(),
                localeHelper.getLocalMessage(I18Constant.UPDATED_SUCCESSFULLY.getCode())));
    }


    @GetMapping("/indexes-order")
    public ResponseEntity<SuccessResponse<LogIndexOrder>>
    getLogPipelinesOrder(@PathVariable final String tenantId,
                         @RequestHeader("user_id") final String userId) {

        var indexNames = this.logIndexPipelineService
                .getIndexNameListOrderByOrder(tenantId);

        return ResponseEntity.ok(new SuccessResponse<>(
                new LogIndexOrder(indexNames),
                HttpStatus.OK.value(),
                localeHelper.getLocalMessage(I18Constant.READ_SUCCESSFULLY.getCode())));
    }

    @PutMapping("/indexes-order")
    public ResponseEntity<SuccessResponse<LogIndexOrder>>
    updateLogPipelinesOrder(@PathVariable final String tenantId,
                            @RequestHeader("user_id") final String userId,
                            @RequestBody @Valid final LogIndexOrder logIndexOrder) {

        var logIndexOrderDto = this.logIndexPipelineMapper
                .toLogIndexOrderDto(tenantId, userId, logIndexOrder);

        logIndexOrderDto = this.logIndexPipelineService
                .updateLogIndexesOrder(tenantId, logIndexOrderDto);

        var _logIndexOrderApiModel = logIndexPipelineMapper
                .fromLogIndexOrderDto(logIndexOrderDto);

        return ResponseEntity.ok(new SuccessResponse<>(_logIndexOrderApiModel,
                HttpStatus.OK.value(),
                localeHelper.getLocalMessage(I18Constant.UPDATED_SUCCESSFULLY.getCode())));
    }


    @GetMapping("/indexes/sample-rate-type")
    public ResponseEntity<SuccessResponse<Category>>
    getLogIndexSampleRateType(@PathVariable final String tenantId,
                                  @RequestHeader("user_id") final String userId) {

        var categoryApiModel = new Category();
        categoryApiModel.setCode("");
        new ArrayList<>(EnumSet.allOf(ExclusionFilterSampleRateType.class))
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
