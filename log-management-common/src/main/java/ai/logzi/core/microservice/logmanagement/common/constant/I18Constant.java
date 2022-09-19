package ai.logzi.core.microservice.logmanagement.common.constant;

import lombok.Getter;

@Getter
public enum I18Constant {

    USER_NOT_AUTHORIZED("user.not.authorized"),

    //region General Messages
    READ_SUCCESSFULLY("read.successfully"),
    CREATED_SUCCESSFULLY("created.successfully"),
    UPDATED_SUCCESSFULLY("updated.successfully"),
    DELETED_SUCCESSFULLY("deleted.successfully"),
    OPERATION_NOT_VALID("operation.not.valid"),
    //endregion

    //region LogPipeline Messages
    LOG_PIPELINE_VALIDATION_ERROR("log.pipeline.validation.errors"),
    LOG_PIPELINE_NOT_FOUND("log.pipeline.absent"),
    LOG_PIPELINE_NAME_IS_EMPTY("log.pipeline.name.absent"),
    LOG_PIPELINE_TYPE_IS_EMPTY("log.pipeline.type.absent"),
    LOG_PIPELINE_TYPE_IS_UNKNOWN("log.pipeline.type.unknown"),
    LOG_PIPELINE_ORDER_ID_LIST_NOT_COMPLETE("log.pipeline.order.id.list.not.complete"),
    UPDATE_ONLY_ONE_OR_TWO_PIPELINE("update.only.one.or.two.pipeline"),
    DELETED_PIPELINE_CAN_NOT_UPDATE("deleted.pipeline.can.not.update"),
    LOG_PROCESSOR_COUNT_IS_INVALID("log.processor.count.is.invalid"),
    LOG_PROCESSOR_NAME_IS_EMPTY("log.processor.name.absent"),
    LOG_PROCESSOR_SOURCE_IS_EMPTY("log.processor.source.absent"),
    LOG_PROCESSOR_TYPE_IS_UNKNOWN("log.processor.type.unknown"),
    LOG_PROCESSOR_TYPE_IS_EMPTY("log.processor.type.absent"),
    LOG_PROCESSOR_GROK_SHOULD_BE_EMPTY("log.processor.grok.should.be.empty"),
    LOG_PROCESSOR_GROK_MATCH_RULE_IS_EMPTY("log.processor.grok.match.rules.absent"),
    LOG_PROCESSOR_GROK_SAMPLES_SHOULD_BE_EMPTY("log.processor.grok.samples.should.be.empty"),
    LOG_PROCESSOR_PIPELINE_NAME_IS_EMPTY("log.processor.pipeline.name.absent"),
    LOG_PROCESSOR_PIPELINE_NESTED_PIPELINE_NOT_VALID("log.processor.pipeline.nested.pipeline.not.valid"),
    LOG_PROCESSOR_ATTRIBUTE_REMAPPER_SOURCE_TYPE_IS_EMPTY("log.processor.attribute.source.type.absent"),
    LOG_PROCESSOR_ATTRIBUTE_REMAPPER_TARGET_TYPE_IS_EMPTY("log.processor.attribute.target.type.absent"),
    LOG_PROCESSOR_TARGET_IS_EMPTY("log.processor.target.absent"),
    LOG_PROCESSOR_ATTRIBUTE_REMAPPER_TARGET_FORMAT_IS_EMPTY("log.processor.attribute.target.format.absent"),
    LOG_PROCESSOR_ATTRIBUTE_REMAPPER_SOURCE_TYPE_IS_UNKNOWN("log.processor.attribute.remapper.source.type.is.unknown"),
    LOG_PROCESSOR_ATTRIBUTE_REMAPPER_TARGET_TYPE_IS_UNKNOWN("log.processor.attribute.remapper.target.type.is.unknown"),
    LOG_PROCESSOR_ATTRIBUTE_REMAPPER_TARGET_FORMAT_IS_UNKNOWN("log.processor.attribute.remapper.target.format.is.unknown"),
    LOG_PROCESSOR_ARITHMETIC_PROCESSOR_EXPRESSION_IS_EMPTY("log.processor.arithmetic.processor.expression.is.empty"),
    LOG_PROCESSOR_STRING_BUILDER_PROCESSOR_TEMPLATE_IS_EMPTY("log.processor.string.builder.processor.template.is.empty"),
    LOG_PROCESSOR_CATEGORY_PROCESSOR_CATEGORY_IS_EMPTY("log.processor.category.processor.category.is.empty"),
    LOG_PROCESSOR_LOOKUP_PROCESSOR_LOOKUP_TABLE_IS_EMPTY("log.processor.lookup.processor.lookup.table.is.empty"),

    //endregion
    ;

    //endregion
    final String code;

    I18Constant(String code) {
        this.code = code;
    }
}