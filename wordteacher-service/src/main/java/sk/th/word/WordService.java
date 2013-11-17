package sk.th.word;

import sk.th.pipifax.LanguagCode;
import sk.th.pipifax.Language;
import sk.th.pipifax.entity.WordEntity;
import sk.th.word.sk.th.word.exception.InvalidFormatException;

import java.util.List;

public interface WordService {

    List<WordEntity> findAllWords(String currentUserName, LanguagCode currentLanguage);

    List<WordEntity> parseWords(String words) throws InvalidFormatException;

    void importWords(List<WordEntity> words, Language language);

    void updateWord(WordEntity currentWord);

    WordEntity loadNextWord(String currentUserName, LanguagCode currentLanguage);
}
