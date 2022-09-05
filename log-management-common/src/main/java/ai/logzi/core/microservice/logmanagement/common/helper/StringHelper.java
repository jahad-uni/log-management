package ai.logzi.core.microservice.logmanagement.common.helper;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class StringHelper {

    public List<String> decodeFilterValues(String str) {
        var valueList = new ArrayList<String>();
        if (str.startsWith("(")) {
            var values = str.split("OR");
            for (String val : values) {
                val = val.trim();
                if (val.startsWith("(")) {
                    StringBuilder sb = new StringBuilder(val);
                    sb.deleteCharAt(0);
                    val = sb.toString();
                }
                if (val.endsWith(")")) {
                    StringBuilder sb = new StringBuilder(val);
                    sb.deleteCharAt(val.length() - 1);
                    val = sb.toString();
                }
                valueList.add(val);
            }
        } else {
            valueList.add(str);
        }
        return valueList
                .stream()
                .distinct()
                .collect(Collectors.toList());
    }

    public boolean isEmpty(String str){

        return str == null || str.isEmpty();
    }
}
