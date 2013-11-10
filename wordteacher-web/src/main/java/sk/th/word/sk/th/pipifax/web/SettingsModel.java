package sk.th.word.sk.th.pipifax.web;

import org.springframework.stereotype.Component;
import sk.th.pipifax.LanguagCode;

/**
 * Created with IntelliJ IDEA.
 * User: tohy
 * Date: 10.11.13
 * Time: 17:21
 * To change this template use File | Settings | File Templates.
 */
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
