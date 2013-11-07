package sk.th.word;

import sk.th.Word;
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

    List<Word> findAllWords();

    List<Word> parseWords(String words) throws InvalidFormatException;

    void importWords(List<Word> words);
}
