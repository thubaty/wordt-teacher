package sk.th.pipifax.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.th.pipifax.Language;
import sk.th.pipifax.LanguageRepository;

import java.util.List;

@Service
public class CodeServiceImpl implements CodeService {

    @Autowired
    LanguageRepository languageRepository;

    @Override
    public List<Language> getAllLanguages() {
        return languageRepository.getAllLanguages();
    }
}
