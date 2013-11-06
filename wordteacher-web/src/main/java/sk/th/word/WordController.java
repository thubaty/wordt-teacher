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
        this.file = readFile();
        List<Word> words = wordService.parseWords(file);
        wordModel.setWords(words);
    }

    public void init() {
        List<Word> words = wordModel.getWords();
        wordModel.setCurrentWord(words.get(0));
    }

    String readFile()
            throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("C:\\Users\\tohy\\IdeaProjects\\wordteacher\\wordteacher-web\\src\\main\\resources\\testdata.txt"));
        StringBuffer stringBuffer = new StringBuffer();
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            stringBuffer.append(line).append("\n");
        }
        System.out.println(stringBuffer);
        return stringBuffer.toString();
    }

    private String word;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public List<String> complete(String query) {
        return wordService.findWords(query);
    }

    public String getText() {
        return "spring hellooouuuuu";
    }

    public List<Word> getWordList() {
        return wordModel.getWords();
    }

    public Integer getWordCount() {
        return wordModel.getWords().size();
    }

    public void dangerActionListener(ActionEvent e) {
        System.out.println("danger");
        Collections.shuffle(wordModel.getWords());
    }

    public String getCurrentUser() {
        return SecurityUtil.getCurrentUserName();
    }


}
