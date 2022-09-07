package ai.logzi.core.microservice.logmanagement.api.controller;

import ai.logzi.core.management.logmanagement.service.LogPipelineService;
import ai.logzi.core.management.logmanagement.service.dto.log.LogPipelineDto;
import ai.logzi.core.microservice.logmanagement.api.mapper.LogPipelineMapper;
import ai.logzi.core.microservice.logmanagement.api.model.log.LogPipeline;
import ai.logzi.core.management.logmanagement.service.constant.I18Constant;
import ai.logzi.core.microservice.logmanagement.common.helper.LocaleHelper;
import ai.logzi.core.microservice.logmanagement.api.model.SuccessResponse;
import ai.logzi.core.microservice.logmanagement.common.validation.OnCreate;
import ai.logzi.core.microservice.logmanagement.common.validation.OnUpdate;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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


    @PostMapping("/pipelines")
    public ResponseEntity<SuccessResponse<LogPipeline>>
    createLogPipeline(@PathVariable final String tenantId,
                      @RequestHeader("user_id") final String userId,
                      @RequestBody @Validated(value = OnCreate.class) final LogPipeline logPipeline) throws Exception {

        var logPipelineDto = this.logPipelineMapper
                .toLogPipeLineDto(tenantId, userId, logPipeline);

        logPipelineDto = this.logPipelineService
                .createLogPipeline(tenantId, logPipelineDto);

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
                      @RequestBody @Validated(value = OnUpdate.class) final LogPipeline logPipeline) throws Exception {

        var logPipelineDto = this.logPipelineMapper
                .toLogPipeLineDto(tenantId, userId, id, logPipeline);

        logPipelineDto = this.logPipelineService
                .updateLogPipeline(tenantId, logPipelineDto);

        var logPipelineApiModel = logPipelineMapper
                .fromLogPipelineDto(logPipelineDto);

        return ResponseEntity.ok(new SuccessResponse<>(logPipelineApiModel,
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

