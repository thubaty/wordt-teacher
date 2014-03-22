package sk.th.word;

import org.apache.log4j.Logger;
import org.omnifaces.util.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import sk.th.pipifax.dto.WordDto;
import sk.th.pipifax.util.SRSUtil;
import sk.th.pipifax.util.SecurityUtil;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import java.io.IOException;
import java.util.Date;

@Controller
@Scope(value = "request")
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
        Long count = wordService.countAllWords(currentUserName, settingsModel.getCurrentLanguage());
        wordModel.setWordCount(count.intValue());
    }

    public void loadWord() {
        String currentUserName = SecurityUtil.getCurrentUserName();
        WordDto wordEntity = wordService.loadNextWord(currentUserName, settingsModel.getCurrentLanguage(), new Date());
        wordModel.setCurrentWord(wordEntity);
        if (wordEntity == null) {
            Messages.addGlobalError("ziadne dalsie slovicka na ucenie kamosko");
        }
    }

    public void initWord() {
        FacesContext currentInstance = FacesContext.getCurrentInstance();
        if (!currentInstance.isPostback()) {
            loadWord();
            updateWordCount();
        }
    }

    public void repetition0(ActionEvent e) {
        updateWord(wordModel.getCurrentWord(), 0);
    }

    public void repetition1(ActionEvent e) {
        updateWord(wordModel.getCurrentWord(), 1);
    }

    public void repetition2(ActionEvent e) {
        updateWord(wordModel.getCurrentWord(), 2);
    }

    public void repetition3(ActionEvent e) {
        updateWord(wordModel.getCurrentWord(), 3);
    }

    public void repetition4(ActionEvent e) {
        updateWord(wordModel.getCurrentWord(), 4);
    }

    public void repetition5(ActionEvent e) {
        updateWord(wordModel.getCurrentWord(), 5);
    }

    Logger logger = Logger.getLogger(this.getClass());

    public void updateWord(WordDto word, int quality) {
        logger.debug("updating word ---------------------");
        WordDto repeatedWord = SRSUtil.repetition(word, quality);
        wordService.updateWord(repeatedWord);
        loadWord();
    }


    public String getCurrentUser() {
        return SecurityUtil.getCurrentUserName();
    }

    public Boolean getUserLoggedIn() {
        return SecurityUtil.isUserLoggedIn();
    }
}
