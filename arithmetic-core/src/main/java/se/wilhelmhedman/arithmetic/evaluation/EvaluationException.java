package se.wilhelmhedman.arithmetic.evaluation;

public class EvaluationException extends Exception {
    private int offendingCharIndex;
    private String advancedMessage;

    public EvaluationException(int offendingCharIndex, String advancedMessage) {
        this.offendingCharIndex = offendingCharIndex;
        this.advancedMessage = advancedMessage;
    }

    public int getOffendingCharIndex() {
        return offendingCharIndex;
    }

    @Override
    public String getMessage() {
        return "Syntax error at character index " + offendingCharIndex + " (" + advancedMessage + ")";
    }
}
