package sk.th.word;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import sk.th.Word;

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
        List<Word> words = wordService.parseWords(importString);
        System.out.println(words.size());
        wordService.importWords(words);
    }
}
