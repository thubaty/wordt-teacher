package sk.th.word;

import org.springframework.stereotype.Component;
import sk.th.pipifax.entity.WordEntity;

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

    private List<WordEntity> words;

    private WordEntity currentWord;

    private Integer wordCount;

    public WordEntity getCurrentWord() {
        return currentWord;
    }

    public void setCurrentWord(WordEntity currentWord) {
        this.currentWord = currentWord;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<WordEntity> getWords() {
        return words;
    }

    public void setWords(List<WordEntity> words) {
        this.words = words;
    }

    public Integer getWordCount() {
        return wordCount;
    }

    public void setWordCount(Integer wordCount) {
        this.wordCount = wordCount;
    }
}
