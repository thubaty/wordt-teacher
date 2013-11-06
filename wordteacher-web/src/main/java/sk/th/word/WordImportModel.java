package sk.th.word;

import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: tohy
 * Date: 06.11.13
 * Time: 21:34
 * To change this template use File | Settings | File Templates.
 */
@Component
public class WordImportModel {

    private String importString;

    public String getImportString() {
        return importString;
    }

    public void setImportString(String importString) {
        this.importString = importString;
    }
}
