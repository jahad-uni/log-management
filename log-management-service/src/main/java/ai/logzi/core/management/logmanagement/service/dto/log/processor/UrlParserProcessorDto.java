package ai.logzi.core.management.logmanagement.service.dto.log.processor;

import lombok.Data;

import java.util.List;

@Data
public class UrlParserProcessorDto {
    private List<String> sources;
    private Boolean normalize_ending_slashes;
    private String target;

}
