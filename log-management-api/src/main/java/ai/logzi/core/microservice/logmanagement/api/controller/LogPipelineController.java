package ai.logzi.core.microservice.logmanagement.api.controller;

import ai.logzi.core.management.logmanagement.service.LogPipelineService;
import ai.logzi.core.management.logmanagement.service.dto.log.LogPipelineDto;
import ai.logzi.core.microservice.logmanagement.api.mapper.LogPipelineMapper;
import ai.logzi.core.microservice.logmanagement.api.model.Category;
import ai.logzi.core.microservice.logmanagement.api.model.CategoryElement;
import ai.logzi.core.microservice.logmanagement.api.model.log.LogPipeline;
import ai.logzi.core.microservice.logmanagement.api.model.log.LogPipelineInput;
import ai.logzi.core.microservice.logmanagement.api.model.log.LogPipelineOrder;
import ai.logzi.core.management.logmanagement.service.constant.I18Constant;
import ai.logzi.core.management.logmanagement.service.constant.TenantConstant;
import ai.logzi.core.microservice.logmanagement.common.helper.LocaleHelper;
import ai.logzi.core.microservice.logmanagement.api.model.SuccessResponse;
import ai.logzi.core.management.logmanagement.service.type.BooleanType;
import ai.logzi.core.management.logmanagement.service.type.LastUpdatedTimeType;
import ai.logzi.core.management.logmanagement.service.type.log.LogPipelineCoreFilterType;
import ai.logzi.core.management.logmanagement.service.type.log.LogPipelineProcessorType;
import ai.logzi.core.management.logmanagement.service.type.log.LogPipelineType;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

@Tag(name = "Log Pipeline Api",
        description = "These Api used to deal with log pipelines")
@RestController
@RequestMapping("/api/v1/tenant/{tenantId}/logs/config/")
@AllArgsConstructor
public class LogPipelineController {

    private final LogPipelineService logPipelineService;
    private final LogPipelineMapper logPipelineMapper;
    private final LocaleHelper localeHelper;

    @GetMapping("/pipelines")
    public ResponseEntity<SuccessResponse<List<LogPipeline>>>
    getAllLogPipelines(@PathVariable final String tenantId,
                       @RequestHeader("user_id") final String userId,
                       @RequestParam final String filter) {

        List<LogPipeline> logPipelineList;
        List<LogPipelineDto> logPipelineDtoList;
        if (filter != null && !filter.isEmpty()) {
            var logPipelineFilterDto = this.logPipelineService
                    .generateLogPipelineFilterDto(filter);
            logPipelineDtoList = this.logPipelineService
                    .getAllLogPipelinesOrderByOrder(tenantId, logPipelineFilterDto);
        } else {
            logPipelineDtoList = this.logPipelineService
                    .getAllLogPipelinesOrderByOrder(tenantId);
        }

        logPipelineList = this.logPipelineMapper
                .fromLogPipelineDtoList(logPipelineDtoList);

        return ResponseEntity.ok(new SuccessResponse<>(logPipelineList,
                HttpStatus.OK.value(),
                localeHelper.getLocalMessage(I18Constant.READ_SUCCESSFULLY.getCode())));
    }

    @GetMapping("/pipelines/library")
    public ResponseEntity<SuccessResponse<List<LogPipeline>>>
    getAllLogPipelinesLibrary(@PathVariable final String tenantId,
                              @RequestHeader("user_id") final String userId,
                              @RequestParam final String filter) throws Exception {

        var logPipelineDtoList = this.logPipelineService
                .getAllLogPipelinesOrderByName(TenantConstant.SHARED.getCode(),
                        LogPipelineType.INTEGRATION.getCode());

        var logPipelineApiModelList = this.logPipelineMapper
                .fromLogPipelineDtoList(logPipelineDtoList);

        return ResponseEntity.ok(new SuccessResponse<>(logPipelineApiModelList,
                HttpStatus.OK.value(),
                localeHelper.getLocalMessage(I18Constant.READ_SUCCESSFULLY.getCode())));
    }

    @GetMapping("/pipelines/{id}")
    public ResponseEntity<SuccessResponse<LogPipeline>>
    getLogPipeline(@PathVariable final String tenantId,
                   @RequestHeader("user_id") final String userId,
                   @PathVariable final String id) {

        var logPipelineDto = this.logPipelineService
                .getLogPipeline(tenantId, id);

        var logPipelineApiModel = this.logPipelineMapper
                .fromLogPipelineDto(logPipelineDto);

        return ResponseEntity.ok(new SuccessResponse<>(logPipelineApiModel,
                HttpStatus.OK.value(),
                localeHelper.getLocalMessage(I18Constant.READ_SUCCESSFULLY.getCode())));
    }

    @GetMapping("/pipelines/cores")
    public ResponseEntity<SuccessResponse<List<Category>>>
    getLogPipelinesFilters(@PathVariable final String tenantId,
                           @RequestHeader("user_id") final String userId) {

        var categoryApiModels = new ArrayList<Category>();
        for (var filterType : new ArrayList<>(EnumSet.allOf(LogPipelineCoreFilterType.class))) {
            var categoryApiModel = new Category();
            if (filterType.getCode().equals(LogPipelineCoreFilterType.IS_ENABLED.getCode())) {
                categoryApiModel.setCode(LogPipelineCoreFilterType.IS_ENABLED.getCode());
                new ArrayList<>(EnumSet.allOf(BooleanType.class))
                        .forEach(f -> {
                            categoryApiModel.getCategoryElements().add(CategoryElement
                                    .builder()
                                    .code(f.getCode())
                                    .build());
                        });
                categoryApiModels.add(categoryApiModel);
            } else if (filterType.getCode().equals(LogPipelineCoreFilterType.IS_EMPTY.getCode())) {
                categoryApiModel.setCode(LogPipelineCoreFilterType.IS_EMPTY.getCode());
                new ArrayList<>(EnumSet.allOf(BooleanType.class))
                        .forEach(f -> {
                            categoryApiModel.getCategoryElements().add(CategoryElement
                                    .builder()
                                    .code(f.getCode())
                                    .build());
                        });
                categoryApiModels.add(categoryApiModel);
            } else if (filterType.getCode().equals(LogPipelineCoreFilterType.IS_READONLY.getCode())) {
                categoryApiModel.setCode(LogPipelineCoreFilterType.IS_READONLY.getCode());
                new ArrayList<>(EnumSet.allOf(BooleanType.class))
                        .forEach(f -> {
                            categoryApiModel.getCategoryElements().add(CategoryElement
                                    .builder()
                                    .code(f.getCode())
                                    .build());
                        });
                categoryApiModels.add(categoryApiModel);
            } else if (filterType.getCode().equals(LogPipelineCoreFilterType.TYPE.getCode())) {
                categoryApiModel.setCode(LogPipelineCoreFilterType.TYPE.getCode());
                new ArrayList<>(EnumSet.allOf(LogPipelineType.class))
                        .forEach(f -> {
                            categoryApiModel.getCategoryElements().add(CategoryElement
                                    .builder()
                                    .code(f.getCode())
                                    .build());
                        });
                categoryApiModels.add(categoryApiModel);
            } else if (filterType.getCode().equals(LogPipelineCoreFilterType.LAST_UPDATED_TIME.getCode())) {
                categoryApiModel.setCode(LogPipelineCoreFilterType.LAST_UPDATED_TIME.getCode());
                new ArrayList<>(EnumSet.allOf(LastUpdatedTimeType.class))
                        .forEach(f -> {
                            categoryApiModel.getCategoryElements().add(CategoryElement
                                    .builder()
                                    .code(f.getCode())
                                    .build());
                        });
                categoryApiModels.add(categoryApiModel);

            } else if (filterType.getCode().equals(LogPipelineCoreFilterType.LAST_UPDATED_BY.getCode())) {
                categoryApiModel.setCode(LogPipelineCoreFilterType.LAST_UPDATED_BY.getCode());
                categoryApiModel.setDynamic(true);
                var userIds = this.logPipelineService
                        .getUpdatedByIdsDistinct(tenantId);
                userIds.forEach(f -> {
                    categoryApiModel.getCategoryElements().add(CategoryElement
                            .builder()
                            .name("farhad")
                            .code(f)
                            .build());
                });
                categoryApiModels.add(categoryApiModel);

            } else if (filterType.getCode().equals(LogPipelineCoreFilterType.CONTAINS.getCode())) {
                categoryApiModel.setCode(LogPipelineCoreFilterType.CONTAINS.getCode());
                new ArrayList<>(EnumSet.allOf(LogPipelineProcessorType.class))
                        .forEach(f -> {
                            categoryApiModel.getCategoryElements().add(CategoryElement
                                    .builder()
                                    .code(f.getCode())
                                    .build());
                        });
                categoryApiModels.add(categoryApiModel);

            }
        }

        return ResponseEntity.ok(new SuccessResponse<>(categoryApiModels,
                HttpStatus.OK.value(),
                localeHelper.getLocalMessage(I18Constant.READ_SUCCESSFULLY.getCode())));
    }

    @GetMapping("/pipelines/processors-type")
    public ResponseEntity<SuccessResponse<Category>>
    getLogPipelinesProcessorsType(@PathVariable final String tenantId,
                                  @RequestHeader("user_id") final String userId) {

        var categoryApiModel = new Category();
        categoryApiModel.setCode("");
        new ArrayList<>(EnumSet.allOf(LogPipelineProcessorType.class))
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

    @GetMapping("/pipelines-order")
    public ResponseEntity<SuccessResponse<LogPipelineOrder>>
    getLogPipelinesOrder(@PathVariable final String tenantId,
                         @RequestHeader("user_id") final String userId) {

        var pipelineIds = this.logPipelineService
                .getLogPipelineIdListOrderByOrder(tenantId);

        return ResponseEntity.ok(new SuccessResponse<>(
                new LogPipelineOrder(pipelineIds),
                HttpStatus.OK.value(),
                localeHelper.getLocalMessage(I18Constant.READ_SUCCESSFULLY.getCode())));
    }

    @PutMapping("/pipelines-order")
    public ResponseEntity<SuccessResponse<LogPipelineOrder>>
    updateLogPipelinesOrder(@PathVariable final String tenantId,
                            @RequestHeader("user_id") final String userId,
                            @RequestBody @Valid final LogPipelineOrder logPipelineOrder) {

        var logPipelineOrderDto = this.logPipelineMapper
                .toLogPipelineOrderDto(tenantId, userId, logPipelineOrder);

        logPipelineOrderDto = this.logPipelineService
                .updateLogPipelinesOrder(tenantId, logPipelineOrderDto);

        var _logPipelineOrderApiModel = logPipelineMapper
                .fromLogPipelineOrderDto(logPipelineOrderDto);

        return ResponseEntity.ok(new SuccessResponse<>(_logPipelineOrderApiModel,
                HttpStatus.OK.value(),
                localeHelper.getLocalMessage(I18Constant.UPDATED_SUCCESSFULLY.getCode())));
    }

    @PostMapping("/pipelines")
    public ResponseEntity<SuccessResponse<LogPipeline>>
    createLogPipeline(@PathVariable final String tenantId,
                      @RequestHeader("user_id") final String userId,
                      @RequestBody @Valid final LogPipelineInput logPipelineInput) throws Exception {

        var logPipelineDto = this.logPipelineMapper
                .toLogPipeLineDto(tenantId, userId, logPipelineInput);

        logPipelineDto = this.logPipelineService
                .createLogPipeline(tenantId, logPipelineDto);

        var logPipelineApiModel = this.logPipelineMapper
                .fromLogPipelineDto(logPipelineDto);

        return ResponseEntity.ok(new SuccessResponse<>(logPipelineApiModel,
                HttpStatus.OK.value(),
                localeHelper.getLocalMessage(I18Constant.CREATED_SUCCESSFULLY.getCode())));
    }

    @PostMapping("/pipelines/{id}/clone")
    public ResponseEntity<SuccessResponse<LogPipeline>>
    cloneLogPipeline(@PathVariable final String tenantId,
                     @RequestHeader("user_id") final String userId,
                     @PathVariable final String id) throws Exception {

        var logPipelineDto = this.logPipelineService
                .cloneLogPipeline(tenantId, userId, id);

        var logPipelineApiModel = this.logPipelineMapper
                .fromLogPipelineDto(logPipelineDto);

        return ResponseEntity.ok(new SuccessResponse<>(logPipelineApiModel,
                HttpStatus.OK.value(),
                localeHelper.getLocalMessage(I18Constant.CREATED_SUCCESSFULLY.getCode())));
    }

    @PutMapping("/pipelines/{id}")
    public ResponseEntity<SuccessResponse<LogPipeline>>
    updateLogPipeline(@PathVariable final String tenantId,
                      @RequestHeader("user_id") final String userId,
                      @PathVariable final String id,
                      @RequestBody final LogPipelineInput logPipelineInput) throws Exception {

        var logPipelineDto = this.logPipelineMapper
                .toLogPipeLineDto(tenantId, userId, id, logPipelineInput);

        logPipelineDto = this.logPipelineService
                .updateLogPipeline(tenantId, logPipelineDto);

        var logPipelineApiModel = logPipelineMapper
                .fromLogPipelineDto(logPipelineDto);

        return ResponseEntity.ok(new SuccessResponse<>(logPipelineApiModel,
                HttpStatus.OK.value(),
                localeHelper.getLocalMessage(I18Constant.UPDATED_SUCCESSFULLY.getCode())));
    }

    @PutMapping("/pipelines")
    public ResponseEntity<SuccessResponse<List<LogPipeline>>>
    updateLogPipelines(@PathVariable final String tenantId,
                       @RequestHeader("user_id") final String userId,
                       @RequestParam final String deletedId,
                       @RequestBody final List<LogPipeline> logPipelines) throws Exception {

        var logPipelineDtoList = this.logPipelineMapper
                .toLogPipeLineDtoList(logPipelines);
        logPipelineDtoList
                .forEach(logPipelineDto -> logPipelineDto.setUserId(userId));

        logPipelineDtoList = this.logPipelineService
                .updateLogPipelines(tenantId, logPipelineDtoList, deletedId);

        var logPipelineApiModelList = logPipelineMapper
                .fromLogPipelineDtoList(logPipelineDtoList);

        return ResponseEntity.ok(new SuccessResponse<>(logPipelineApiModelList,
                HttpStatus.OK.value(),
                localeHelper.getLocalMessage(I18Constant.UPDATED_SUCCESSFULLY.getCode())));
    }


    @DeleteMapping("/pipelines/{id}")
    public ResponseEntity<SuccessResponse<Object>>
    deleteLogPipeline(@PathVariable final String tenantId,
                      @RequestHeader("user_id") final String userId,
                      @PathVariable final String id) {

        this.logPipelineService
                .deleteLogPipeline(tenantId, userId, id);

        return ResponseEntity.ok(new SuccessResponse<>(null,
                HttpStatus.OK.value(),
                localeHelper.getLocalMessage(I18Constant.DELETED_SUCCESSFULLY.getCode())));
    }

    @DeleteMapping("/pipelines")
    public ResponseEntity<SuccessResponse<Object>>
    deleteAllLogPipelines(@PathVariable final String tenantId,
                          @RequestHeader("user_id") final String userId) {

        this.logPipelineService
                .deleteAllLogPipelines(tenantId);

        return ResponseEntity.ok(new SuccessResponse<>(null,
                HttpStatus.OK.value(),
                localeHelper.getLocalMessage(I18Constant.DELETED_SUCCESSFULLY.getCode())));
    }

}

