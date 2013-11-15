package sk.th.word;

import sk.th.pipifax.LanguagCode;
import sk.th.pipifax.Language;
import sk.th.pipifax.entity.WordEntity;
import sk.th.word.sk.th.word.exception.InvalidFormatException;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tohy
 * Date: 07.10.13
 * Time: 19:41
 * To change this template use File | Settings | File Templates.
 */
public interface WordService {

    List<WordEntity> findAllWords(String currentUserName, LanguagCode currentLanguage);

    List<WordEntity> parseWords(String words) throws InvalidFormatException;

    void importWords(List<WordEntity> words, Language language);

    void updateWord(WordEntity currentWord);
}
