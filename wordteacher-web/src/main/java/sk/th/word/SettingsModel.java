package sk.th.word;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import sk.th.pipifax.LanguageCode;

import java.util.List;

@Component
@Scope(value = "session")
public class SettingsModel {

    public SettingsModel() {
        System.out.println("cons");
    }

    private List<LanguageCode> userLanguages;

    private LanguageCode currentLanguage;

    public LanguageCode getCurrentLanguage() {
        return currentLanguage;
    }

    public void setCurrentLanguage(LanguageCode currentLanguage) {
        this.currentLanguage = currentLanguage;
    }

    public List<LanguageCode> getUserLanguages() {
        return userLanguages;
    }

    public void setUserLanguages(List<LanguageCode> userLanguages) {
        this.userLanguages = userLanguages;
    }
}
