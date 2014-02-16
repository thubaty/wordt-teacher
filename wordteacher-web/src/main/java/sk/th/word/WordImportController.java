package sk.th.word;

import org.omnifaces.util.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import sk.th.pipifax.dto.WordDto;
import sk.th.word.sk.th.word.exception.InvalidFormatException;

import javax.faces.event.ActionEvent;
import java.util.List;

@Controller
public class WordImportController {

    @Autowired
    private WordController wordController;

    @Autowired
    private WordService wordService;

    @Autowired
    private WordImportModel wordImportModel;

    public void importActionListener(ActionEvent e) {
        String importString = wordImportModel.getImportString();
        List<WordDto> words = null;
        try {
            words = wordService.parseWords(importString);
        } catch (InvalidFormatException ex) {
            List<String> lines = ex.getLine();
            for (String line : lines) {
                Messages.addGlobalError(line);
                return;
            }
        }
        wordService.importWords(words, wordImportModel.getLanguage());
        wordController.updateWordCount();
        Messages.addGlobalInfo("{0} lines imported", words.size());
        wordImportModel.setImportString(null);
    }
}
