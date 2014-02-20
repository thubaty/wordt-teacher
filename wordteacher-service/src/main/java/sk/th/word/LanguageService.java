package sk.th.word;

import sk.th.pipifax.LanguageCode;

import java.util.List;

public interface LanguageService {

    List<LanguageCode> getAllLanguages();

    List<LanguageCode> getAllLanguagesForUser(String userName);

}
