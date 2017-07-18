package se.wilhelmhedman.androidarithmetic.listeners;

import android.view.View;

import java.util.HashMap;
import java.util.Map;

import se.wilhelmhedman.androidarithmetic.R;
import se.wilhelmhedman.androidarithmetic.widget.CalculatorInputTextView;
import se.wilhelmhedman.androidarithmetic.widget.EvaluationErrorNotificationFacade;

public class SpecialButtonListener extends LiteralButtonListener {
    public static final Map<Integer, String> SPECIAL_BUTTON_ACTIONS = new HashMap<Integer, String>() {{
        put(R.id.buttonSin, "sin(");
        put(R.id.buttonCos, "cos(");
        put(R.id.buttonTan, "tan(");
        put(R.id.buttonLog, "log(");
        put(R.id.buttonLn, "ln(");
    }};

    public SpecialButtonListener(CalculatorInputTextView typingTextView, EvaluationErrorNotificationFacade errorNotificationFacade) {
        super(typingTextView, errorNotificationFacade);
    }

    @Override
    public void onClick(View view) {
        typingTextView.addText(SPECIAL_BUTTON_ACTIONS.get(view.getId()));
        errorNotificationFacade.clearEvaluationError();
    }
}
