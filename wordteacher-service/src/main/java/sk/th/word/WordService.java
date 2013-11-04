package sk.th.word;

import sk.th.Word;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tohy
 * Date: 07.10.13
 * Time: 19:41
 * To change this template use File | Settings | File Templates.
 */
public interface WordService {

    List<String> findAllWords();

    List<String> findWords(String word);

    List<Word> parseWords(String words);
}
