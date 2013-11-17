package sk.th.word;

import org.omnifaces.util.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import sk.th.pipifax.entity.WordEntity;
import sk.th.pipifax.util.SRSUtil;
import sk.th.pipifax.util.SecurityUtil;
import sk.th.word.sk.th.pipifax.web.SettingsModel;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Calendar;
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

    public void loadWord() {
        String currentUserName = SecurityUtil.getCurrentUserName();
        WordEntity wordEntity = wordService.loadNextWord(currentUserName, settingsModel.getCurrentLanguage());
        wordModel.setCurrentWord(wordEntity);
        if (wordEntity == null) {
            Messages.addGlobalError("ziadne dalsie slovicka na ucenie kamosko");
        }
    }

    public void initWord() {
        FacesContext currentInstance = FacesContext.getCurrentInstance();
        if (!currentInstance.isPostback()) {
            loadWord();
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
        int interval = SRSUtil.calcuateInterval(word.getEFactor(), word.getInterval(), word.getCount());
        word.setInterval(interval);
        word.setEFactor(SRSUtil.scoreCard(word.getEFactor(), quality));
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.HOUR, interval*24);
        word.setNextRepetition(new Timestamp(instance.getTime().getTime()));
        wordService.updateWord(word);
        loadWord();
    }


    public String getCurrentUser() {
        return SecurityUtil.getCurrentUserName();
    }
}
