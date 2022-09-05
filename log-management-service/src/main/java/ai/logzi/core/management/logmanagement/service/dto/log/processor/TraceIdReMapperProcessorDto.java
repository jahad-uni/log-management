package ai.logzi.core.management.logmanagement.service.dto.log.processor;

import lombok.Data;

import java.util.List;

@Data
public class TraceIdReMapperProcessorDto {
    private List<String> sources;
}
