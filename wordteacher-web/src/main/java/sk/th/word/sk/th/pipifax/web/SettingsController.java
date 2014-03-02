package sk.th.word.sk.th.pipifax.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import sk.th.pipifax.LanguageCode;
import sk.th.pipifax.util.SecurityUtil;
import sk.th.word.LanguageService;
import sk.th.word.WordController;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import java.util.Collections;
import java.util.List;

@Controller
public class SettingsController {

    @Autowired
    private WordController wordController;

    @Autowired
    SettingsModel settingsModel;

    @Autowired
    LanguageService languageService;

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
