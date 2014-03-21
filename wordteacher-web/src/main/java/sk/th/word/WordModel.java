package sk.th.word;

import org.springframework.stereotype.Component;
import sk.th.pipifax.dto.WordDto;

import java.util.Date;

@Component
public class WordModel {

    private WordDto currentWord;

    private Integer wordCount;

    private Date currentDate;

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
