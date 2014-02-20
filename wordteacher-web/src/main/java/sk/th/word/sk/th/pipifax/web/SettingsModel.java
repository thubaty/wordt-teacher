package sk.th.word.sk.th.pipifax.web;

import org.springframework.stereotype.Component;
import sk.th.pipifax.LanguageCode;

@Component
public class SettingsModel {

    private LanguageCode currentLanguage;

    public LanguageCode getCurrentLanguage() {
        return currentLanguage;
    }

    public void setCurrentLanguage(LanguageCode currentLanguage) {
        this.currentLanguage = currentLanguage;
    }
}
