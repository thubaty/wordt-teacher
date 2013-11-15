package sk.th.word;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import sk.th.pipifax.entity.WordEntity;
import sk.th.pipifax.util.SRSUtil;
import sk.th.pipifax.util.SecurityUtil;
import sk.th.word.sk.th.pipifax.web.SettingsModel;

import javax.faces.event.ActionEvent;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;

@Controller
public class WordController {

    private final WordService wordService;

    private final WordModel wordModel;

    private final SettingsModel settingsModel;

    @Autowired
    public WordController(WordService wordService, WordModel wordModel, SettingsModel settingsModel) throws IOException {
        this.wordService = wordService;
        this.wordModel = wordModel;
        this.settingsModel = settingsModel;
    }

    public void updateWordCount() {
        String currentUserName = SecurityUtil.getCurrentUserName();
        List<WordEntity> allWords = wordService.findAllWords(currentUserName, settingsModel.getCurrentLanguage());
        wordModel.setWordCount(allWords.size());
    }

    public void initTemplate() {
        if (wordModel.getWordCount() == null) {
            updateWordCount();
        }
    }

    public void initWord() {
        String currentUserName = SecurityUtil.getCurrentUserName();
        if (wordModel.getWordCount() != null && wordModel.getWordCount() > 0) {
            List<WordEntity> allWords = wordService.findAllWords(currentUserName, settingsModel.getCurrentLanguage());
            Collections.shuffle(allWords);
            wordModel.setCurrentWord(allWords.get(0));
        } else {
            wordModel.setCurrentWord(null);
        }
    }

    public void easypeasyActionListener(ActionEvent e) {
        updateWord(wordModel.getCurrentWord(), 5);
    }

    public void solalaActionListener(ActionEvent e) {
        updateWord(wordModel.getCurrentWord(), 3);
    }


    public void donnoActionListener(ActionEvent e) {
        updateWord(wordModel.getCurrentWord(), 1);
    }

    public void updateWord(WordEntity word, int quality) {
        word.setModified(new Timestamp(System.currentTimeMillis()));
        word.setCount(word.getCount() + 1);
        word.setInterval(SRSUtil.calcuateInterval(word.getEFactor(), word.getInterval(), word.getCount()));
        word.setEFactor(SRSUtil.scoreCard(word.getEFactor(), quality));
        wordService.updateWord(word);
        initWord();
    }


    public String getCurrentUser() {
        return SecurityUtil.getCurrentUserName();
    }
}
