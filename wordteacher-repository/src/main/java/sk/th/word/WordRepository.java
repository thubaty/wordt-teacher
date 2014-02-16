package sk.th.word;

import sk.th.pipifax.LanguagCode;
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

    List<WordDto> findAll(String currentUserName, LanguagCode currentLanguage);

    void importWords(List<WordDto> words, Language language);

    void updateWord(WordDto currentWord);

    WordDbEntity loadScheduledWords(String currentUserName, LanguagCode currentLanguage);

    UserWordEntity loadLearningData(String currentUserName, WordDbEntity wordDbEntity);

    WordDbEntity loadWordsWithLowQuality(String currentUserName, LanguagCode currentLanguage);
}
