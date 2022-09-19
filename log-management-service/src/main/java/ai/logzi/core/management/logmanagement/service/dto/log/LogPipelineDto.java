package ai.logzi.core.management.logmanagement.service.dto.log;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogPipelineDto {

    private String id;
    private String name;
    private List<String> tags;
    private String description;
    private List<String> filters;
    private String type;
    private boolean is_enabled;
    private boolean is_readonly;
    private int order;

    private List<LogPipelineProcessorDto> processors = new ArrayList<>();

    private String userId;

    private String updatedBy;
    private LocalDateTime updatedAt;

    private String emailBox;

    private String updatedTime;

}
