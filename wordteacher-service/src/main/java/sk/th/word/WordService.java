package sk.th.word;

import sk.th.pipifax.LanguagCode;
import sk.th.pipifax.Language;
import sk.th.pipifax.dto.WordDto;
import sk.th.word.sk.th.word.exception.InvalidFormatException;

import java.util.List;

public interface WordService {

    Long countAllWords(String currentUserName, LanguagCode currentLanguage);

    List<WordDto> parseWords(String words) throws InvalidFormatException;

    void importWords(List<WordDto> words, Language language);

    void updateWord(WordDto currentWord);

    WordDto loadNextWord(String currentUserName, LanguagCode currentLanguage);
}
