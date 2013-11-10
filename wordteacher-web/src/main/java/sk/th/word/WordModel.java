package sk.th.word;

import org.springframework.stereotype.Component;
import sk.th.pipifax.entity.WordEntity;

import java.util.List;

@Component
public class WordModel {

    private WordEntity currentWord;

    private Integer wordCount;

    public WordEntity getCurrentWord() {
        return currentWord;
    }

    public void setCurrentWord(WordEntity currentWord) {
        this.currentWord = currentWord;
    }

    public Integer getWordCount() {
        return wordCount;
    }

    public void setWordCount(Integer wordCount) {
        this.wordCount = wordCount;
    }
}
