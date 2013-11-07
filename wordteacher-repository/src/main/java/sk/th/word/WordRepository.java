package sk.th.word;

import sk.th.Word;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tohy
 * Date: 04.11.13
 * Time: 17:41
 * To change this template use File | Settings | File Templates.
 */
public interface WordRepository {

    List<Word> findAll();

    void importWords(List<Word> words);
}
