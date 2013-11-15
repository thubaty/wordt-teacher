package sk.th.word;

import sk.th.pipifax.LanguagCode;
import sk.th.pipifax.Language;
import sk.th.pipifax.entity.WordEntity;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tohy
 * Date: 04.11.13
 * Time: 17:41
 * To change this template use File | Settings | File Templates.
 */
public interface WordRepository {

    List<WordEntity> findAll(String currentUserName, LanguagCode currentLanguage);

    void importWords(List<WordEntity> words, Language language);

    void updateWord(WordEntity currentWord);
}
