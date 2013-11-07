package sk.th.word.sk.th.word.exception;

public class InvalidFormatException extends Exception {

    private String line;

    public InvalidFormatException(String line) {
        super("Cannot parse input - " + line);
        this.line = line;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }
}
