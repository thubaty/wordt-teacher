package sk.th.word;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.th.pipifax.LanguageCode;
import sk.th.pipifax.Language;
import sk.th.pipifax.LanguageRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tohy on 18.02.14.
 */
@Service
public class LanguageServiceImpl implements LanguageService {

    @Autowired
    LanguageRepository languageRepository;

    @Override
    public List<LanguageCode> getAllLanguages() {
        List<Language> allLanguages = languageRepository.getAllLanguages();
        List<LanguageCode> allCodes = new ArrayList<LanguageCode>();
        for (Language allLanguage : allLanguages) {
            allCodes.add(allLanguage.getCode());
        }
        return allCodes;
    }

    @Override
    public List<LanguageCode> getAllLanguagesForUser(String userName) {
        List<Language> allLanguages = languageRepository.getAllLanguagesForUser(userName);
        List<LanguageCode> allCodes = new ArrayList<LanguageCode>();
        for (Language allLanguage : allLanguages) {
            allCodes.add(allLanguage.getCode());
        }
        return allCodes;
    }
}
