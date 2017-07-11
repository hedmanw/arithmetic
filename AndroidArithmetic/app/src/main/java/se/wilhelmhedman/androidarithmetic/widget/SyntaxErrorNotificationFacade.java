package se.wilhelmhedman.androidarithmetic.widget;

import se.wilhelmhedman.androidarithmetic.calc.SyntaxErrorResponse;

public class SyntaxErrorNotificationFacade {
    private CalculatorInputTextView inputTextView;
    private SyntaxErrorTextView errorContainer;

    public SyntaxErrorNotificationFacade(CalculatorInputTextView inputTextView, SyntaxErrorTextView errorContainer) {
        this.inputTextView = inputTextView;
        this.errorContainer = errorContainer;
    }

    public void setSyntaxError(SyntaxErrorResponse syntaxErrorResponse) {
        errorContainer.show(syntaxErrorResponse.toString());
        inputTextView.setSyntaxError(syntaxErrorResponse.getOffendingIndex());
    }

    public void clearSyntaxError() {
        errorContainer.hide();
        inputTextView.clearSyntaxError();
    }
}
