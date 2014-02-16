package sk.th.pipifax.generator;

public class ExcelUserTag {

    private String user;
    private String tag;

    public ExcelUserTag(String user, String tag) {
        this.user = user;
        this.tag = tag;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
