package sk.th.pipifax.generator;

/**
 * Created by tohy on 15.02.14.
 */
public class ExcelWord {

    private Long id;
    private String slovak;
    private String translation;
    private String tag;

    public ExcelWord(Long id, String slovak, String translation, String tag) {
        this.id = id;
        this.slovak = slovak;
        this.translation = translation;
        this.tag = tag;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
