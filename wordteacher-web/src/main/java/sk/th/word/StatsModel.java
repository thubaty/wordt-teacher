package sk.th.word;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import sk.th.pipifax.Language;

import javax.faces.bean.ViewScoped;

@Component
@ViewScoped
public class StatsModel {

    private String importString;

    private Language language;

    public String getImportString() {
        return importString;
    }

    public void setImportString(String importString) {
        this.importString = importString;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }
}
