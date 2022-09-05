package ai.logzi.core.management.logmanagement.service.dto.log.processor;

import lombok.Data;

import java.util.List;

@Data
public class UserAgentParserProcessorDto  {

    private List<String> sources;
    private Boolean is_encoded;
    private String target;
}
