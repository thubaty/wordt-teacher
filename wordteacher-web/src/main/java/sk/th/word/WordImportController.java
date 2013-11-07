package sk.th.word;

import org.omnifaces.util.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import sk.th.Word;
import sk.th.word.sk.th.word.exception.InvalidFormatException;

import javax.faces.event.ActionEvent;
import java.util.List;

@Controller
public class WordImportController {

    @Autowired
    private WordService wordService;

    @Autowired
    private WordImportModel wordImportModel;

    public void importActionListener(ActionEvent e) {
        String importString = wordImportModel.getImportString();
        List<Word> words = null;
        try {
            words = wordService.parseWords(importString);
        } catch (InvalidFormatException ex) {
            Messages.addGlobalError("Cannot parse file - " + ex.getLine());
        }
        wordService.importWords(words);
        Messages.addGlobalInfo("{0} lines imported", words.size());
    }
}
