package sk.th.pipifax.generator;

/**
 * Created by tohy on 15.02.14.
 */
public class ExcelLanguage {

    private Long id;
    private String name;

    public ExcelLanguage(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
