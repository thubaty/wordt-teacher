package sk.th.word;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import sk.th.pipifax.LanguageCode;
import sk.th.pipifax.util.SecurityUtil;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import java.util.Collections;
import java.util.List;

@Controller
@Scope(value = "request")
public class SettingsController {


    private WordController wordController;


    SettingsModel settingsModel;


    LanguageService languageService;

    @Autowired
    public SettingsController(WordController wordController, SettingsModel settingsModel, LanguageService languageService) {
        this.wordController = wordController;
        this.settingsModel = settingsModel;
        this.languageService = languageService;
    }

    public void init() {
        if (!FacesContext.getCurrentInstance().isPostback()) {
            if (settingsModel.getCurrentLanguage() == null) {
                List<LanguageCode> allLanguages = languageService.getAllLanguagesForUser(SecurityUtil.getCurrentUserName());
                settingsModel.setCurrentLanguage(allLanguages.get(0));
                settingsModel.setUserLanguages(allLanguages);
            }
        }
    }

    public void switchLanguage(ActionEvent e) {
        List<LanguageCode> allLanguages = settingsModel.getUserLanguages();
        Collections.rotate(allLanguages, allLanguages.indexOf(settingsModel.getCurrentLanguage()) + 1);
        LanguageCode newLanguage = allLanguages.get(0);
        settingsModel.setCurrentLanguage(newLanguage);
        wordController.loadWord();
        wordController.updateWordCount();
    }

    public Boolean getHasMoreThanOneLanguage() {
        if (settingsModel.getUserLanguages() == null) {
            return false;
        }
        return settingsModel.getUserLanguages().size() > 1;
    }

    public String getLanguageHtmlColor() {
        LanguageCode currentLanguage = settingsModel.getCurrentLanguage();
        if (LanguageCode.EN.equals(currentLanguage)) {
            return "#3276b1";
        } else if (LanguageCode.DE.equals(currentLanguage)) {
            return "#8A4B08";
        } else {
            return "#FFFFFF";
        }
    }

    public String getLanguageName() {
        LanguageCode currentLanguage = settingsModel.getCurrentLanguage();
        if (LanguageCode.EN.equals(currentLanguage)) {
            return "english";
        } else if (LanguageCode.DE.equals(currentLanguage)) {
            return "german";
        } else {
            return currentLanguage.getCode();
        }
    }

    public void switchUser(ActionEvent e) {
        System.out.println("switch");
    }

    public void switchToEnglish(ActionEvent e) {
        settingsModel.setCurrentLanguage(LanguageCode.EN);
        wordController.updateWordCount();
    }

    public void switchToGerman(ActionEvent e) {
        settingsModel.setCurrentLanguage(LanguageCode.DE);
        wordController.updateWordCount();
    }
}
