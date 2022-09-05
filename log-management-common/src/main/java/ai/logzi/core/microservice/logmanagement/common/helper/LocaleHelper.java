package ai.logzi.core.microservice.logmanagement.common.helper;

import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class LocaleHelper {

    private final MessageSource messageSource;

    public String getLocalMessage(String key, String... params) {
        return messageSource.getMessage(key, params, LocaleContextHolder.getLocale());
    }
}
