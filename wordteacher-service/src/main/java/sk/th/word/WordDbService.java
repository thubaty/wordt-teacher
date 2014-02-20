package sk.th.word;

import sk.th.pipifax.LanguageCode;
import sk.th.pipifax.entity.WordDbEntity;

import java.util.List;

public interface WordDbService {

    List<WordDbEntity> findAllWords(LanguageCode currentLanguage);
}
