package sk.th.word;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import sk.th.pipifax.Language;
import sk.th.pipifax.service.CodeService;

import java.util.List;

@Controller
public class CodeController {

    @Autowired
    CodeService codeService;

    public List<Language> getLanguages() {
        return codeService.getAllLanguages();
    }

}
