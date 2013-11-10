package sk.th.pipifax.dto;

import sk.th.pipifax.LanguagCode;

/**
 * Created with IntelliJ IDEA.
 * User: tohy
 * Date: 07.11.13
 * Time: 22:21
 * To change this template use File | Settings | File Templates.
 */
public class WordDto {

    private String slovak;

    private String translation;

    private LanguagCode languagCode;

    public String getSlovak() {
        return slovak;
    }

    public void setSlovak(String slovak) {
        this.slovak = slovak;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }
}
