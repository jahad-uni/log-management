package ai.logzi.core.microservice.logmanagement.api.controller;

import ai.logzi.core.management.logmanagement.service.LogMetricPipelineService;
import ai.logzi.core.management.logmanagement.service.constant.I18Constant;
import ai.logzi.core.management.logmanagement.service.dto.logMetric.LogMetricPipelineDto;
import ai.logzi.core.management.logmanagement.service.type.logMetric.ComputeAggregationType;
import ai.logzi.core.microservice.logmanagement.api.mapper.LogMetricPipelineMapper;
import ai.logzi.core.microservice.logmanagement.api.model.Category;
import ai.logzi.core.microservice.logmanagement.api.model.CategoryElement;
import ai.logzi.core.microservice.logmanagement.api.model.SuccessResponse;
import ai.logzi.core.microservice.logmanagement.api.model.logMetric.LogMetricPipeline;
import ai.logzi.core.microservice.logmanagement.api.model.logMetric.LogMetricPipelineInput;
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

@Tag(name = "Log Metric Api",
        description = "These Api used to deal with Log Metric")
@RestController
@RequestMapping("/api/v1/tenant/{tenantId}/logs/config/")
@AllArgsConstructor
public class LogMetricPipelineController {
    private final LogMetricPipelineService logMetricPipelineService;
    private final LogMetricPipelineMapper logMetricPipelineMapper;
    private final LocaleHelper localeHelper;

    @GetMapping("/metrics")
    public ResponseEntity<SuccessResponse<List<LogMetricPipeline>>>
    getAllLogMetrics(@PathVariable final String tenantId,
                     @RequestHeader("user_id") final String userId) {

        List<LogMetricPipelineDto> logMetricPipelineDtoList = this.logMetricPipelineService
                .getAllLogMetrics(tenantId);

        List<LogMetricPipeline> logMetricPipelineList = this.logMetricPipelineMapper
                .fromLogMetricPipelineDtoList(logMetricPipelineDtoList);

        return ResponseEntity.ok(new SuccessResponse<>(logMetricPipelineList,
                HttpStatus.OK.value(),
                localeHelper.getLocalMessage(I18Constant.READ_SUCCESSFULLY.getCode())));
    }


    @GetMapping("/metrics/{id}")
    public ResponseEntity<SuccessResponse<LogMetricPipeline>>
    getLogMetric(@PathVariable final String tenantId,
                 @RequestHeader("user_id") final String userId,
                 @PathVariable final String id) {

        var logMetricPipelineDto = this.logMetricPipelineService
                .getLogMetric(tenantId, id);

        var logMetricPipelineApiModel = this.logMetricPipelineMapper
                .fromLogMetricPipelineDto(logMetricPipelineDto);

        return ResponseEntity.ok(new SuccessResponse<>(logMetricPipelineApiModel,
                HttpStatus.OK.value(),
                localeHelper.getLocalMessage(I18Constant.READ_SUCCESSFULLY.getCode())));
    }


    @PostMapping("/metrics")
    public ResponseEntity<SuccessResponse<LogMetricPipeline>>
    createLogMetric(@PathVariable final String tenantId,
                    @RequestHeader("user_id") final String userId,
                    @RequestBody @Valid final LogMetricPipelineInput logMetricPipelineInput) throws Exception {

        var logMetricPipelineDto = this.logMetricPipelineMapper
                .toLogMetricPipelineDto(tenantId, userId, logMetricPipelineInput);

        logMetricPipelineDto = this.logMetricPipelineService
                .createLogMetric(tenantId, logMetricPipelineDto);

        var logMetricPipelineApiModel = this.logMetricPipelineMapper
                .fromLogMetricPipelineDto(logMetricPipelineDto);

        return ResponseEntity.ok(new SuccessResponse<>(logMetricPipelineApiModel,
                HttpStatus.OK.value(),
                localeHelper.getLocalMessage(I18Constant.CREATED_SUCCESSFULLY.getCode())));
    }


    @PutMapping("/metrics/{id}")
    public ResponseEntity<SuccessResponse<LogMetricPipeline>>
    updateLogMetric(@PathVariable final String tenantId,
                    @RequestHeader("user_id") final String userId,
                    @PathVariable final String id,
                    @RequestBody final LogMetricPipelineInput logMetricPipelineInput) throws Exception {

        var logMetricPipelineDto = this.logMetricPipelineMapper
                .toLogMetricPipelineDto(tenantId, userId, id, logMetricPipelineInput);

        logMetricPipelineDto = this.logMetricPipelineService
                .updateLogMetric(tenantId, id, logMetricPipelineDto);

        var logMetricPipelineApiModel = logMetricPipelineMapper
                .fromLogMetricPipelineDto(logMetricPipelineDto);

        return ResponseEntity.ok(new SuccessResponse<>(logMetricPipelineApiModel,
                HttpStatus.OK.value(),
                localeHelper.getLocalMessage(I18Constant.UPDATED_SUCCESSFULLY.getCode())));
    }


    @DeleteMapping("/metrics/{id}")
    public ResponseEntity<SuccessResponse<Object>>
    deleteLogMetric(@PathVariable final String tenantId,
                    @RequestHeader("user_id") final String userId,
                    @PathVariable final String id) {

        this.logMetricPipelineService
                .deleteLogMetric(tenantId, userId, id);

        return ResponseEntity.ok(new SuccessResponse<>(null,
                HttpStatus.OK.value(),
                localeHelper.getLocalMessage(I18Constant.DELETED_SUCCESSFULLY.getCode())));
    }


    @GetMapping("/metrics/compute-aggregation-type")
    public ResponseEntity<SuccessResponse<Category>>
    getLogMetricsComputeAggregationType(@PathVariable final String tenantId,
                                        @RequestHeader("user_id") final String userId) {

        var categoryApiModel = new Category();
        categoryApiModel.setCode("");
        new ArrayList<>(EnumSet.allOf(ComputeAggregationType.class))
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
