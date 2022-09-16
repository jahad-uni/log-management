package ai.logzi.core.microservice.logmanagement.api.controller;

import ai.logzi.core.management.logmanagement.service.LogPipelineService;
import ai.logzi.core.management.logmanagement.service.dto.log.LogPipelineDto;
import ai.logzi.core.microservice.logmanagement.api.mapper.LogPipelineMapper;
import ai.logzi.core.microservice.logmanagement.api.model.log.LogPipeline;
import ai.logzi.core.management.logmanagement.service.constant.I18Constant;
import ai.logzi.core.microservice.logmanagement.api.model.log.LogPipelineProcessor;
import ai.logzi.core.microservice.logmanagement.common.helper.LocaleHelper;
import ai.logzi.core.microservice.logmanagement.api.model.SuccessResponse;
import ai.logzi.core.microservice.logmanagement.common.validation.OnCreate;
import ai.logzi.core.microservice.logmanagement.common.validation.OnUpdate;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Tag(name = "Log Pipeline Api",
        description = "These Api used to deal with log pipelines")
@RestController
@RequestMapping("/api/v1/tenant/{tenantId}/logs/config/")
@AllArgsConstructor
public class LogPipelineController {

    //region Dependencies

    private final LogPipelineService logPipelineService;
    private final LogPipelineMapper logPipelineMapper;
    private final LocaleHelper localeHelper;

    //endregion

    //region Rest API

    //@GetMapping("/pipelines")
    @RequestMapping(value = "/pipelines", method = RequestMethod.GET)
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

        this.addLogPipelinesLinks(tenantId, userId, logPipelineList);

        return ResponseEntity.ok(new SuccessResponse<>(logPipelineList,
                HttpStatus.OK.value(),
                localeHelper.getLocalMessage(I18Constant.READ_SUCCESSFULLY.getCode())));
    }


    @GetMapping(value = "/pipelines/{id}", produces = {"application/hal+json"})
    public ResponseEntity<SuccessResponse<LogPipeline>>
    getLogPipeline(@PathVariable final String tenantId,
                   @RequestHeader("user_id") final String userId,
                   @PathVariable final String id) {

        var logPipelineDto = this.logPipelineService
                .getLogPipeline(tenantId, id);

        var logPipeline = this.logPipelineMapper
                .fromLogPipelineDto(logPipelineDto);

        this.addLogPipelineLinks(tenantId, userId, logPipeline);

        return ResponseEntity.ok(new SuccessResponse<>(logPipeline,
                HttpStatus.OK.value(),
                localeHelper.getLocalMessage(I18Constant.READ_SUCCESSFULLY.getCode())));
    }

    @GetMapping(value = "/pipelines/{id}/processors")
    public ResponseEntity<SuccessResponse<List<LogPipelineProcessor>>>
    getLogPipelineProcessors(@PathVariable final String tenantId,
                             @RequestHeader("user_id") final String userId,
                             @PathVariable final String id) {

        var logPipelineDto = this.logPipelineService
                .getLogPipeline(tenantId, id);

        var logPipeline = this.logPipelineMapper
                .fromLogPipelineDto(logPipelineDto);

        return ResponseEntity.ok(new SuccessResponse<>(logPipeline.getProcessors(),
                HttpStatus.OK.value(),
                localeHelper.getLocalMessage(I18Constant.READ_SUCCESSFULLY.getCode())));
    }

    @RequestMapping(value = "/pipelines", method = RequestMethod.POST)
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


    //@PutMapping("/pipelines/{id}")
    @RequestMapping(value = "/pipelines/{id}", method = RequestMethod.PUT)
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

    //endregion

    //region Private Methods

    private void addLogPipelineLinks(String tenantId, String userId, LogPipeline logPipeline) {

        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
                        .methodOn(LogPipelineController.class)
                        .getLogPipeline(tenantId, userId, logPipeline.getId())).withSelfRel();
        logPipeline.add(selfLink);

        if (logPipeline.getProcessors().size() > 0) {
            Link processorsLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
                            .methodOn(LogPipelineController.class)
                            .getLogPipelineProcessors(tenantId, userId, logPipeline.getId())).withRel("processors");
            logPipeline.add(processorsLink);
        }
    }

    private void addLogPipelinesLinks(String tenantId, String userId, List<LogPipeline> logPipelines){
        for(LogPipeline logPipeline : logPipelines){
            addLogPipelineLinks(tenantId, userId, logPipeline);
        }
    }

    //endregion
}

