package se.wilhelmhedman.androidarithmetic.widget;

import se.wilhelmhedman.androidarithmetic.calc.ErrorResponse;

public class EvaluationErrorNotificationFacade {
    private CalculatorInputTextView inputTextView;
    private SyntaxErrorTextView errorContainer;

    public EvaluationErrorNotificationFacade(CalculatorInputTextView inputTextView, SyntaxErrorTextView errorContainer) {
        this.inputTextView = inputTextView;
        this.errorContainer = errorContainer;
    }

    public void setEvaluationError(ErrorResponse errorResponse) {
        errorContainer.show(errorResponse.toString());
        inputTextView.setEvaluationError(errorResponse.getOffendingIndex());
    }

    public void clearEvaluationError() {
        errorContainer.hide();
        inputTextView.clearEvaluationError();
    }
}
