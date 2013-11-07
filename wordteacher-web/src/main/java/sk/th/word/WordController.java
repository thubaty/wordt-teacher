package sk.th.word;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import sk.th.Word;
import sk.th.wordmanager.SecurityUtil;

import javax.faces.event.ActionEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tohy
 * Date: 07.10.13
 * Time: 20:00
 * To change this template use File | Settings | File Templates.
 */
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

    public void init() {
    }

    private String word;

    public List<Word> getWordList() {
        return wordModel.getWords();
    }

    public Integer getWordCount() {
        return 1000;
    }

    public void dangerActionListener(ActionEvent e) {
        System.out.println("danger");
        Collections.shuffle(wordModel.getWords());
    }

    public String getCurrentUser() {
        return SecurityUtil.getCurrentUserName();
    }


}
