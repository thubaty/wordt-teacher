package sk.th.word;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import sk.th.Word;
import sk.th.wordmanager.SecurityUtil;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Controller
public class WordController {

    private final WordService wordService;
    private final WordModel wordModel;

    private String file;

    @Autowired
    public WordController( WordService wordService, WordModel wordModel) throws IOException {
        this.wordService = wordService;
        this.wordModel = wordModel;
    }

    @PostConstruct
    private void init() {
        List<Word> allWords = wordService.findAllWords();
        wordModel.setWordCount(allWords.size());
    }

    public void initWord() {
        List<Word> allWords = wordService.findAllWords();
        Collections.shuffle(allWords);
        wordModel.setCurrentWord(allWords.get(0));
    }

    public void dangerActionListener(ActionEvent e) {
        initWord();
    }

    public String getCurrentUser() {
        return SecurityUtil.getCurrentUserName();
    }
}
