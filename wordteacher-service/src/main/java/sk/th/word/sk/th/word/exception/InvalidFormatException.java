package sk.th.word.sk.th.word.exception;

import java.util.List;

public class InvalidFormatException extends Exception {

    private List<String> line;

    public InvalidFormatException(List<String> line) {
        super("Cannot parse input");
        this.line = line;
    }

    public List<String> getLine() {
        return line;
    }

    public void setLine(List<String> line) {
        this.line = line;
    }
}
