package sk.th.word;

import org.springframework.stereotype.Component;
import sk.th.pipifax.dto.WordDto;

@Component
public class WordModel {

    private WordDto currentWord;

    private Integer wordCount;

    public WordDto getCurrentWord() {
        return currentWord;
    }

    public void setCurrentWord(WordDto currentWord) {
        this.currentWord = currentWord;
    }

    public Integer getWordCount() {
        return wordCount;
    }

    public void setWordCount(Integer wordCount) {
        this.wordCount = wordCount;
    }
}
