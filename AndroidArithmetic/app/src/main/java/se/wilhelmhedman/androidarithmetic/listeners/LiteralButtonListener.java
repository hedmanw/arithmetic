package se.wilhelmhedman.androidarithmetic.listeners;

import android.view.View;
import android.widget.Button;

import se.wilhelmhedman.androidarithmetic.R;
import se.wilhelmhedman.androidarithmetic.widget.CalculatorInputTextView;
import se.wilhelmhedman.androidarithmetic.widget.EvaluationErrorNotificationFacade;

public class LiteralButtonListener implements View.OnClickListener {
    public static final int[] LITERAL_BUTTONS = new int[] {
            R.id.button0,
            R.id.button1,
            R.id.button2,
            R.id.button3,
            R.id.button4,
            R.id.button5,
            R.id.button6,
            R.id.button7,
            R.id.button8,
            R.id.button9,
            R.id.buttonDecimal,
            R.id.buttonRParen,
            R.id.buttonLParen,
            R.id.buttonAns,
    };

    final CalculatorInputTextView typingTextView;
    final EvaluationErrorNotificationFacade errorNotificationFacade;

    public LiteralButtonListener(CalculatorInputTextView typingTextView, EvaluationErrorNotificationFacade errorNotificationFacade) {
        this.typingTextView = typingTextView;
        this.errorNotificationFacade = errorNotificationFacade;
    }

    @Override
    public void onClick(View view) {
        Button self = (Button) view;
        typingTextView.addText(self.getText());
        errorNotificationFacade.clearEvaluationError();
    }
}
