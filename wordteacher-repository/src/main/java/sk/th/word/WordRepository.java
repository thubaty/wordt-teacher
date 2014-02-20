package sk.th.word;

import sk.th.pipifax.LanguageCode;
import sk.th.pipifax.Language;
import sk.th.pipifax.entity.UserWordEntity;
import sk.th.pipifax.entity.WordDbEntity;
import sk.th.pipifax.dto.WordDto;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tohy
 * Date: 04.11.13
 * Time: 17:41
 * To change this template use File | Settings | File Templates.
 */
public interface WordRepository {

    List<WordDto> findAll(String currentUserName, LanguageCode currentLanguage);

    void importWords(List<WordDto> words, Language language);

    void updateWord(UserWordEntity currentWord);

    WordDbEntity loadScheduledWords(String currentUserName, LanguageCode currentLanguage);

    UserWordEntity loadLearningData(String currentUserName, WordDbEntity wordDbEntity);

    WordDbEntity loadWordsWithLowQuality(String currentUserName, LanguageCode currentLanguage);
}
