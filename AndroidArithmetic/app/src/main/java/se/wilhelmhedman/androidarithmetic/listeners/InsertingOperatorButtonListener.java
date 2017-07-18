package se.wilhelmhedman.androidarithmetic.listeners;

import android.view.View;

import se.wilhelmhedman.androidarithmetic.R;
import se.wilhelmhedman.androidarithmetic.widget.CalculatorInputTextView;
import se.wilhelmhedman.androidarithmetic.widget.EvaluationErrorNotificationFacade;

public class InsertingOperatorButtonListener extends LiteralButtonListener {
    public static final int[] INSERTING_OPERATOR_BUTTONS = new int[] {
            R.id.buttonAdd,
            R.id.buttonSub,
            R.id.buttonMul,
            R.id.buttonDiv,
            R.id.buttonPow,
            R.id.buttonSquare,
    };

    public InsertingOperatorButtonListener(CalculatorInputTextView typingTextView, EvaluationErrorNotificationFacade errorNotificationFacade) {
        super(typingTextView, errorNotificationFacade);
    }

    @Override
    public void onClick(View view) {
        if (typingTextView.getText().length() == 0) {
            typingTextView.addText("ans");
        }
        super.onClick(view);
    }
}
