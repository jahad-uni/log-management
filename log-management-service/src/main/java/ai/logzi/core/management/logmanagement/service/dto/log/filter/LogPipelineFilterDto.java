package ai.logzi.core.management.logmanagement.service.dto.log.filter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogPipelineFilterDto {

    private List<Boolean> isEnabled = new ArrayList<>();
    private List<Boolean> _isEnabled = new ArrayList<>();
    private List<Boolean> isReadonly = new ArrayList<>();
    private List<Boolean> _isReadonly = new ArrayList<>();
    private List<Boolean> isEmpty = new ArrayList<>();
    private List<Boolean> _isEmpty = new ArrayList<>();
    private List<String> types = new ArrayList<>();
    private List<String> _types = new ArrayList<>();
    private List<String> updatedBys = new ArrayList<>();
    private List<String> _updatedBys = new ArrayList<>();
    private List<String> lastUpdatedTimes = new ArrayList<>();
    private List<String> _lastUpdatedTimes = new ArrayList<>();
    private List<String> processorTypes = new ArrayList<>();
    private List<String> _processorTypes = new ArrayList<>();


}
