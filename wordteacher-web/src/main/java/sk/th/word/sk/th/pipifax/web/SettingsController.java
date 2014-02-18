package sk.th.word.sk.th.pipifax.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import sk.th.pipifax.LanguagCode;
import sk.th.pipifax.Language;
import sk.th.pipifax.LanguageRepository;
import sk.th.word.LanguageService;
import sk.th.word.WordController;

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

    public void switchLanguage(ActionEvent e) {
        List<LanguagCode> allLanguages = languageService.getAllLanguages();
        Collections.rotate(allLanguages, allLanguages.indexOf(settingsModel.getCurrentLanguage())+1);
        LanguagCode newLanguage = allLanguages.get(0);
        settingsModel.setCurrentLanguage(newLanguage);
        wordController.loadWord();
        wordController.updateWordCount();
    }

    public void switchToEnglish(ActionEvent e) {
        settingsModel.setCurrentLanguage(LanguagCode.EN);
        wordController.updateWordCount();
    }

    public void switchToGerman(ActionEvent e) {
        settingsModel.setCurrentLanguage(LanguagCode.DE);
        wordController.updateWordCount();
    }
}
