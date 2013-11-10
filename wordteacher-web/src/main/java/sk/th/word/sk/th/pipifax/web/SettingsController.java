package sk.th.word.sk.th.pipifax.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import sk.th.pipifax.LanguagCode;

import javax.faces.event.ActionEvent;

@Controller
public class SettingsController {


    @Autowired
    SettingsModel settingsModel;

    public void switchToEnglish(ActionEvent e) {
        settingsModel.setCurrentLanguage(LanguagCode.EN);
    }

    public void switchToGerman(ActionEvent e) {
        settingsModel.setCurrentLanguage(LanguagCode.DE);
    }
}
