package sk.th.word;

import org.springframework.stereotype.Component;
import sk.th.Word;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tohy
 * Date: 27.10.13
 * Time: 15:36
 * To change this template use File | Settings | File Templates.
 */
@Component
public class WordModel {

    private String text;

    private List<Word> words;

    private Word currentWord;

    private Integer wordCount;

    public Word getCurrentWord() {
        return currentWord;
    }

    public void setCurrentWord(Word currentWord) {
        this.currentWord = currentWord;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Word> getWords() {
        return words;
    }

    public void setWords(List<Word> words) {
        this.words = words;
    }

    public Integer getWordCount() {
        return wordCount;
    }

    public void setWordCount(Integer wordCount) {
        this.wordCount = wordCount;
    }
}
