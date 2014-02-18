package sk.th.word;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.th.pipifax.LanguagCode;
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
    public List<LanguagCode> getAllLanguages() {
        List<Language> allLanguages = languageRepository.getAllLanguages();
        List<LanguagCode> allCodes = new ArrayList<LanguagCode>();
        for (Language allLanguage : allLanguages) {
            allCodes.add(allLanguage.getCode());
        }
        return allCodes;
    }
}
