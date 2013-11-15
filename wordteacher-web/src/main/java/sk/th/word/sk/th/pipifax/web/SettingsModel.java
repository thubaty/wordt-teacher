package sk.th.word.sk.th.pipifax.web;

import org.springframework.stereotype.Component;
import sk.th.pipifax.LanguagCode;

@Component
public class SettingsModel {

    private LanguagCode currentLanguage = LanguagCode.EN;

    public LanguagCode getCurrentLanguage() {
        return currentLanguage;
    }

    public void setCurrentLanguage(LanguagCode currentLanguage) {
        this.currentLanguage = currentLanguage;
    }
}
